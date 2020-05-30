import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.DeciderBuilder;
import scala.concurrent.duration.Duration;

public class Server extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    ActorRef stores = getContext().actorOf(Props.create(Store.class), "stores");

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, product -> {
                    log.info("Received request: What is the price of: " + product);
                    ActorRef store1 = context().actorOf(Props.create(Store.class));
                    ActorRef store2 = context().actorOf(Props.create(Store.class));
                    PriceRequest request = new PriceRequest(product);
                    store1.tell(request, getSelf());
                    store2.tell(request, getSelf());
                })
                .match(PriceResponse.class, response -> {
                    log.info("Received response for " + response.getProduct() + ": " + response.getPrice());
                })
                .matchAny(o -> log.info("Server: received unknown message: " + o))
                .build();
    }

    private static final SupervisorStrategy strategy
            = new OneForOneStrategy(10, Duration.create("1 minute"), DeciderBuilder.
            matchAny(o -> (SupervisorStrategy.Directive) SupervisorStrategy.resume()).
            build());

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

}


