import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.DeciderBuilder;
import scala.concurrent.duration.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    int requestID = 0;
    Map<Integer, ActorRef> clients = new HashMap<>();


    @Override
    public Receive createReceive() {
        List<PriceResponse> priceResponses = new ArrayList<>();
        return receiveBuilder()
                .match(PriceRequest.class, request -> {
                    log.info("Received request. What is the price of: " + request.getProduct());
                    ActorRef client = getSender();
                    clients.put(requestID, client);
                    ActorRef store1 = context().actorOf(Props.create(Store.class));
                    ActorRef store2 = context().actorOf(Props.create(Store.class));
                    store1.tell(request, getSelf());
                    store2.tell(request, getSelf());
                })
                .match(PriceResponse.class, response -> {
                    log.info("Received response for " + response.getProduct() + ": " + response.getPrice());
                    priceResponses.add(response);
                    if(priceResponses.size() == 2){

                        PriceResponse response1 = priceResponses.get(0);
                        PriceResponse response2 = priceResponses.get(1);
                        if(response1.getPrice() < response2.getPrice()){
                            System.out.println("Price = " + response1.getPrice());
                            clients.get(requestID).tell(response1, getSelf());
                        }
                        else{
                            System.out.println("Price = " + response2.getPrice());
                            clients.get(requestID).tell(response2, getSelf());
                        }
                    }
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


