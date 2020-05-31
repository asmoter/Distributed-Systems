import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class ClientApp {


    public static void main(String[] args) throws Exception {

        // config
        File configFile = new File("src/main/client_app.conf");
        Config config = ConfigFactory.parseFile(configFile);

        // create actor system & actors
        final ActorSystem system = ActorSystem.create("client_system", config);
        final ActorRef client = system.actorOf(Props.create(Client.class), "client");

        System.out.println("Enter product name to find out what is the best price, or 'q' to quit");
        // interaction
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = br.readLine();
            if (line.equals("q")) {
                break;
            }
            PriceRequest request = new PriceRequest(line);
            client.tell(request, null);
        }
        system.terminate();
    }
}
