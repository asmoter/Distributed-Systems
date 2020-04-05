
// zleca wykonanie trzech typów usług

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;


public class Agency {


    private static String  AGENCY_NAME;
    private static Channel channel;
    private static String EXCHANGE_NAME = "AgencyTransporterExchange";

    private static void initialize_agency() throws IOException {
        System.out.println("Enter Agency name: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        AGENCY_NAME = br.readLine();
        System.out.println("Agency \"" + AGENCY_NAME +  "\" connected");
    }

    private static void initialize_connection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        channel = connection.createChannel();

//        Exchange
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
    }

    private static void initialize_queues(){

    }

    public static void main(String[] argv) throws Exception {

        initialize_connection();
        initialize_agency();
        initialize_queues();


        while(true){

//            System.out.println("Commision Key: // nr zlecenia");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String commisionID = br.readLine();
            System.out.println("Which service would you like to order - PT, CT, LS: ");
            String service = br.readLine();

            if(service.equals("PT") || service.equals("CT") || service.equals("LS")){
                channel.basicPublish(EXCHANGE_NAME, service, null, service.getBytes("UTF-8"));
                System.out.println("Sent: " + service + "nr " + commisionID);
            }
            else{
                System.out.println("Incorrect commision type. Try again...");
            }
        }
    }
}
