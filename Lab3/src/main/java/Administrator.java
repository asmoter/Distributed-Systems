import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Administrator {

    private static Channel channel;
    private static String EXCHANGE_NAME = "CommissionExchange";
    private static String ADMIN_EXCHANGE_NAME = "AdminExchange";
    private static String ADMIN_NAME = "admin";


    public static void main(String[] args) throws Exception {

        initializeConnection();
        initializeQueue();

        Thread.sleep(300);
        Thread listeningThread = new Thread(new Runnable() {
            public void run() {
                try {
                    listenForMsg();
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        });

        Thread messagingThread = new Thread(new Runnable() {
            public void run() {
                try {
                    sendMsg();
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        });

        listeningThread.start();
        messagingThread.start();
        listeningThread.join();
        messagingThread.join();
    }

    private static void initializeConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        channel.exchangeDeclare(ADMIN_EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
    }

    private static void initializeQueue() throws IOException {

        String bindingKey = "#";
        channel.queueDeclare(ADMIN_NAME, true, true, true, null);
        channel.queueBind(ADMIN_NAME, EXCHANGE_NAME, bindingKey);

        System.out.println("Initialized admin queue - key: " + bindingKey + "\n");
    }

    private static void listenForMsg() throws IOException {

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                channel.basicAck(envelope.getDeliveryTag(), false);
                System.out.println("-> " + message);
            }
        };
        channel.basicConsume(ADMIN_NAME, false, consumer);
    }

    private static void sendMsg() throws IOException {

        System.out.println("Available message modes: \n" +
                "1) A - send msg to all agencies\n" +
                "2) T - send msg to all transporters\n" +
                "3) AT - send msg to all agencies and transporters");

        while (true) {
            System.out.println("Choose message mode:");
            String mode;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            do {
                mode = br.readLine();
            }
            while (!isModeValid(mode));

            System.out.println("Enter msg:");
            String msg = br.readLine();
            processMsg(mode, msg);
        }
    }

    private static boolean isModeValid(String mode){
        if(mode.equals("A") || mode.equals("T") || mode.equals("AT")){
            return true;
        }
        else{
            System.out.println("Incorrect mode. Choose one of the following: A, T, AT");
            return false;
        }
    }

    private static void processMsg(String mode, String message) throws IOException {
        String msg = "Admin: " + message;
        if(mode.equals("A")){
            msgAllAgencies(msg);
        }
        else if(mode.equals("T")){
            msgAllTransporters(msg);
        }
        else{
            msgAll(msg);
        }
    }

    private static void msgAllAgencies(String msg) throws IOException {
        String agenciesBindingKey = "allAgencies";
        channel.basicPublish(ADMIN_EXCHANGE_NAME, agenciesBindingKey, null, msg.getBytes("UTF-8"));
    }

    private static void msgAllTransporters(String msg) throws IOException {
        String transportersBindingKey = "allTransporters";
        channel.basicPublish(ADMIN_EXCHANGE_NAME, transportersBindingKey, null, msg.getBytes("UTF-8"));
    }

    private static void msgAll(String msg) throws IOException {
        msgAllAgencies(msg);
        msgAllTransporters(msg);
    }
}
