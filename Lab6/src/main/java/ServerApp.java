import Actors.Server;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class ServerApp {

    public static void main(String[] args) throws Exception {

        // config
        File configFile = new File("src/main/server_app.conf");
        Config config = ConfigFactory.parseFile(configFile);

        // create actor system & actors
        final ActorSystem system = ActorSystem.create("server_system", config);
        final ActorRef server = system.actorOf(Props.create(Server.class), "server");

        System.out.println("Actors.Server started...");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = br.readLine();
            if (line.equals("q")) {
                break;
            }
        }

        system.terminate();
    }
}