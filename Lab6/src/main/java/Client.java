import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Client extends AbstractActor {

    // for logging
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private static final String SERVER_PATH = "akka://server_system@127.0.0.1:2552/user/server";

    // must be implemented -> creates initial behaviour
    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .match(PriceRequest.class, request -> {
                    getContext().actorSelection(SERVER_PATH).tell(request, getSelf());
                    log.info("Sending price request for: " + request.getProduct());
                })
                .match(PriceResponse.class, response -> {
                    log.info("Client received response: " + response);
                    System.out.println("Price for " + response.getProduct() + ": " + response.getPrice() + "PLN");
                })
                .matchAny(o -> log.info("Client: Received unknown message: " + o))
                .build();
    }
}
