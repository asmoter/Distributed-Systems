import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Client extends AbstractActor {

    // for logging
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private static final String SERVER_PATH = "akka://server_system@127.0.0.1:2551/user/server";

    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .match(PriceRequest.class, request -> {
                    getContext().actorSelection(SERVER_PATH).tell(request, getSelf());
                    log.info("Sending price request for: " + request.getProduct());
                })
                .match(PriceResponse.class, response -> {
                    log.info("Client received response: " + response);
                    String product = response.getProduct();
                    int price = response.getPrice();
                    if(price == -1){
                        System.out.println("No information about price of: " + product);
                    }
                    else System.out.println("Price for " + product + ": " + price + "PLN");
                })
                .matchAny(o -> log.info("Client: Received unknown message: " + o))
                .build();
    }
}
