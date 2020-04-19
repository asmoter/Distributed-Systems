import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Transporter {

    private static Channel channel;
    private static String COMPANY_NAME;
    private static String EXCHANGE_NAME = "CommissionExchange";
    private static String ADMIN_EXCHANGE_NAME = "AdminExchange";
    private static String QUEUE_NAME_1;
    private static String QUEUE_NAME_2;
    private static Service service_1;
    private static Service service_2;
    private static String ADMIN_QUEUE;

    public static void main(String[] argv) throws Exception {

        initializeConnection();
        initializeTransporter();
        initializeServices();
        initializeQueues();

        waitForComissions();
    }

    private static void initializeConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        channel.exchangeDeclare(ADMIN_EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
    }

    private static void initializeTransporter() throws IOException {
        System.out.println("Enter company name: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        COMPANY_NAME = br.readLine();
        System.out.println("Transporter \"" + COMPANY_NAME + "\" connected\n");
    }

    private static void initializeServices() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter available services: \n" +
                "\t PT - passanger transport\n" +
                "\t CT - cargo transport\n" +
                "\t LS - launch satellite\n");
        try{
            do{
                System.out.print("Enter service 1: ");
                service_1 = new Service(br.readLine());
            }
            while(!service_1.isValid());
            do{
                System.out.print("Enter service 2: ");
                service_2 = new Service(br.readLine());
            }
            while(!service_2.isValid());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initializeQueues() throws IOException {
        get_queue_names();
        String bindingKey1 = "transporter." + service_1.getType();
        String bindingKey2 = "transporter." + service_2.getType();
        String adminBindingKey = "allTransporters";
        ADMIN_QUEUE = "admin_" + COMPANY_NAME;

        channel.queueDeclare(QUEUE_NAME_1, true, false, true, null);
        channel.queueBind(QUEUE_NAME_1, EXCHANGE_NAME, bindingKey1);

        channel.queueDeclare(QUEUE_NAME_2, true, false, true, null);
        channel.queueBind(QUEUE_NAME_2, EXCHANGE_NAME, bindingKey2);

        channel.basicQos(1);

        channel.queueDeclare(ADMIN_QUEUE, true, true, true, null);
        channel.queueBind(ADMIN_QUEUE, ADMIN_EXCHANGE_NAME, adminBindingKey);
    }

    private static void get_queue_names() {
        QUEUE_NAME_1 = service_1.getType() + "_queue";
        QUEUE_NAME_2 = service_2.getType() + "_queue";
    }

    private static void waitForComissions() throws IOException {

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                if(message.contains("Admin:")){
                    System.out.println(" ~ Msg from " + message);
                }
                else{
                    processCommission(message);
                }
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        // start listening
        System.out.println("Waiting for commissions...");
        channel.basicConsume(QUEUE_NAME_1, false, consumer);
        channel.basicConsume(QUEUE_NAME_2, false, consumer);
        channel.basicConsume(ADMIN_QUEUE, false, consumer);
    }

    private static void processCommission(String message){
        System.out.println("Received commission: " + message);
        try {
            Thread.sleep(600);
            processAcknowledgement(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processAcknowledgement(String message) throws Exception {
        String serviceType;
        String commissionID;
        String agencyName;
        String bindingKey;

        String[] msg = message.split(", ");
        serviceType = msg[0].split(" = ")[1];
        commissionID = msg[1].split(" = ")[1];
        agencyName = msg[2].split(" = ")[1];

        String acknowledgement = "Company " + COMPANY_NAME + " received commission " + serviceType +
                " (commissionID =  " + commissionID + ") from agency: " + agencyName;

        bindingKey = "agency." + agencyName;

        channel.basicPublish(EXCHANGE_NAME, bindingKey, null, acknowledgement.getBytes("UTF-8"));

        System.out.println("\tAck sent: " + acknowledgement);
    }
}

