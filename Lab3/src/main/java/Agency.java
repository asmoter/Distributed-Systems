
// zleca wykonanie trzech typów usług

import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Agency {


    private static String  AGENCY_NAME;
    private static Channel channel;
    private static String EXCHANGE_NAME = "AgencyTransporterExchange";

    public static void main(String[] argv) throws Exception {

        initializeConnection();
        initializeAgency();
        initializeQueue();

        Thread commissions = new Thread(new Runnable() {
            public void run() {
                try {
                    makeCommission();
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        });
        Thread.sleep(300);
        Thread acknowledgement = new Thread(new Runnable() {
            public void run() {
                try {
                    waitForAcknowledgement();
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        });

        commissions.start();
        acknowledgement.start();
        commissions.join();
        acknowledgement.join();

    }

    private static void initializeConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        channel = connection.createChannel();

//        Exchange
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
    }

    private static void initializeAgency() throws IOException {
        System.out.println("Enter Agency name: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        AGENCY_NAME = br.readLine();
        System.out.println("Agency \"" + AGENCY_NAME +  "\" connected");
    }

    private static void initializeQueue() throws IOException {
        channel.queueDeclare(AGENCY_NAME, true, false, false, null); //t, f, f, n
        String bindingKey = "agency." + AGENCY_NAME;
        channel.queueBind(AGENCY_NAME, EXCHANGE_NAME, bindingKey);

        System.out.println("Initialized queue: \n" +
                "1) " + AGENCY_NAME + " - key: " + bindingKey + "\n");
    }

    private static void makeCommission() throws IOException{

        while(true){

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("-> Which service would you like to order - PT, CT, LS: ");
            String service = br.readLine();

            if(service.equals("PT") || service.equals("CT") || service.equals("LS")){

                String bindingKey = "transporter." + service;
                System.out.println("Commission ID: ");
                String commissionID = br.readLine();

                String msg = "Type = " + service + ", commissionID = " + commissionID + ", agency = " + AGENCY_NAME;
                channel.basicPublish(EXCHANGE_NAME, bindingKey, null, msg.getBytes("UTF-8"));
                System.out.println("\tSend commission: " + msg);
                System.out.println("\tWaiting for ack...");
            }
            else {
                System.out.println("Incorrect commission type. Try again...");
                break;
            }
        }
    }

    private static void waitForAcknowledgement() throws IOException {
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("\tAck: " + message);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        // start listening
        channel.basicConsume(AGENCY_NAME, false, consumer);
    }
}
