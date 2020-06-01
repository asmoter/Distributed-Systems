package Actors;

import Messages.DatabaseResponse;
import Messages.PriceRequest;
import Messages.PriceResponse;
import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Client extends AbstractActor {

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
//                    log.info("Client received response: " + response.getProduct());
                    String product = response.getProduct();
                    int price = response.getPrice();
                    if(price == -1){
                        System.out.println("~~~ No information about price of: " + product);
                    }
                    else {
                        System.out.println("~~~ Price for " + product + ": " + price + "PLN");
                    }
                })
                .match(DatabaseResponse.class, response -> {
                    System.out.println("~~~ Number of queries for " + response.getProduct() + ": " + response.getQueryCounter());
                })
                .matchAny(o -> log.info("Client: Received unknown message: " + o))
                .build();
    }
}
