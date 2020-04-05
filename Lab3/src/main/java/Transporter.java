import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Transporter {

    private static String COMPANY_NAME;
    private static String EXCHANGE_NAME = "AgencyTransporterExchange";
    private static String QUEUE_NAME_1;
    private static String QUEUE_NAME_2;
    private static Service service_1;
    private static Service service_2;
    private static Channel channel;


    public static void main(String[] argv) throws Exception {

        initializeConnection();
        initializeTransporter();
        initializeServices();
        initializeQueues();

        processComissions();
    }

    private static void initializeConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
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

        channel.queueDeclare(QUEUE_NAME_1, true, false, false, null);
        String bindingKey1 = "transporter." + service_1.getType();
        channel.queueBind(QUEUE_NAME_1, EXCHANGE_NAME, bindingKey1);

        channel.queueDeclare(QUEUE_NAME_2, true, false, false, null);
        String bindingKey2 = "transporter." + service_2.getType();
        channel.queueBind(QUEUE_NAME_2, EXCHANGE_NAME, bindingKey2);

        channel.basicQos(1);

        System.out.println("Initialized queues: \n" +
                "1) " + QUEUE_NAME_1 + " - key: " + bindingKey1 + "\n" +
                "2) " + QUEUE_NAME_2 + " - key: " + bindingKey2 + "\n");
    }

    private static void get_queue_names() {
        QUEUE_NAME_1 = service_1.getType() + "_queue";
        QUEUE_NAME_2 = service_2.getType() + "_queue";
    }

    private static void processComissions() throws IOException {

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Received commission: " + message);
                channel.basicAck(envelope.getDeliveryTag(), false);
                try {
                    Thread.sleep(600);
                    processAcknowledgement(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // start listening
        System.out.println("Waiting for commissions...");
        channel.basicConsume(QUEUE_NAME_1, false, consumer); // true?
        channel.basicConsume(QUEUE_NAME_2, false, consumer); // true?

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

