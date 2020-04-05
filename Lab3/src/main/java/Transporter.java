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

        initialize_connection();
        initialize_transporter();
        initialize_services();
        initialize_queues();

        process_comission();
    }

    private static void initialize_connection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
    }

    private static void initialize_transporter() throws IOException {

        System.out.println("Enter company name: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        COMPANY_NAME = br.readLine();
        System.out.println("Transporter \"" + COMPANY_NAME + "\" connected");
    }


    private static void initialize_services() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter available services: \n" +
                "\t PT - passanger transport\n" +
                "\t CT - cargo transport\n" +
                "\t LS - launch satellite\n");
        try {
            service_1 = new Service(br.readLine());
            System.out.println("Service 1: " + service_1.getType());
            service_2 = new Service(br.readLine());
            System.out.println("Servive 2: " + service_2.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void queues() throws IOException {
        channel.queueDeclare(QUEUE_NAME_1, false, false, false, null);
//        System.out.println("Commision Key: // nr zlecenia");
        System.out.print("Binding key queue 1: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String bindingKey1 = br.readLine();
        channel.queueBind(QUEUE_NAME_1, EXCHANGE_NAME, bindingKey1);
        channel.queueDeclare(QUEUE_NAME_2, false, false, false, null);
        System.out.print("Binding key queue 1: ");
        String bindingKey2 = br.readLine();
        channel.queueBind(QUEUE_NAME_2, EXCHANGE_NAME, bindingKey2);

    }


    private static void initialize_queues() throws IOException {
        get_queue_names();

        channel.queueDeclare(QUEUE_NAME_1, true, false, false, null);
        String bindingKey1 = service_1.getType();
        channel.queueBind(QUEUE_NAME_1, EXCHANGE_NAME, bindingKey1);

        channel.queueDeclare(QUEUE_NAME_2, true, false, false, null);
        String bindingKey2 = service_2.getType();
        channel.queueBind(QUEUE_NAME_2, EXCHANGE_NAME, bindingKey2);

        channel.basicQos(1);
//        channel.queueDeclare("adminmode" + name, true, true, true, null).getQueue(); //queue for admin mode
//        channel.queueBind("adminmode" + name, EXCHANGE_NAME, "carriers");

        System.out.print("Initialized queues: \n" +
                "1) " + QUEUE_NAME_1 + " - key: " + bindingKey1 + "\n" +
                "2) " + QUEUE_NAME_2 + " - key: " + bindingKey2 + "\n");
    }

    private static void get_queue_names() {
        QUEUE_NAME_1 = service_1.getType() + "_queue";
        QUEUE_NAME_2 = service_2.getType() + "_queue";
    }

    private static void process_comission() throws IOException {
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Received: " + message);
            }
        };

        // start listening
        System.out.println("Waiting for messages...");
        channel.basicConsume(QUEUE_NAME_1, false, consumer);
        channel.basicConsume(QUEUE_NAME_2, true, consumer);
    }

}

