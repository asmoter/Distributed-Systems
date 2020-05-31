import Actors.Client;
import Messages.PriceRequest;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClientApp {


    public static void main(String[] args) throws Exception {

        System.out.println("Enter client port:");
        // interaction
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String port = br.readLine();

        String conf1 = "akka {\n" +
                "  actor {\n provider = remote\n" +
                "        serializers {\n proto = \"akka.remote.serialization.ProtobufSerializer\"\n}\n" +
                "        serialization-bindings {\n \"Messages.PriceRequest\" = proto\n \"Messages.PriceResponse\" = proto\n }}\n" +
                "  remote.artery {\n canonical {\n hostname = \"127.0.0.1\"\n port = ";
        String conf2 = "}}}";

        String configString = conf1 + port + conf2;
        Config config = ConfigFactory.parseString(configString);

        // create actor system & actors
        final ActorSystem system = ActorSystem.create("client_system", config);
        final ActorRef client = system.actorOf(Props.create(Client.class), "client");

        System.out.println("Enter product name to find out what is the best price, or 'q' to quit");
        while (true) {
            String line = br.readLine();
            if (line.equals("q")) {
                break;
            }
            PriceRequest request = new PriceRequest(port, -1, line);
            client.tell(request, null);
        }
        system.terminate();
    }
}
