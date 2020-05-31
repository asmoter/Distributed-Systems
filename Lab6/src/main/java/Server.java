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

    private int requestID = 0;
    Map<Integer, ActorRef> clients = new HashMap<>();
    List<PriceResponse> priceResponses = new ArrayList<>();

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(PriceRequest.class, request -> {
                    log.info("Received request. What is the price of: " + request.getProduct());
                    ActorRef client = getSender();
                    clients.put(requestID, client);

                    context().child("store1").get().tell(request, getSelf());
                    context().child("store2").get().tell(request, getSelf());

                    getContext().system().scheduler()
                            .scheduleOnce(java.time.Duration.ofMillis(300), () ->
                                client.tell(returnPrice(request), getSelf()),
                                context().system().dispatcher());
                    requestID++;
                })
                .match(PriceResponse.class, response -> {
                    log.info("Received response for " + response.getProduct() + ": " + response.getPrice());
                    priceResponses.add(response);
                })
                .matchAny(o -> log.info("Server: received unknown message: " + o))
                .build();
    }

    private PriceResponse returnPrice(PriceRequest request){
        if(priceResponses.size() == 2){
            PriceResponse response;
            PriceResponse response1 = priceResponses.get(0);
            PriceResponse response2 = priceResponses.get(1);
            if(response1.getPrice() < response2.getPrice()){
                System.out.println("Price = " + response1.getPrice());
                response = response1;
            }
            else{
                System.out.println("Price = " + response2.getPrice());
                response = response2;
            }
            priceResponses.remove(response1);
            priceResponses.remove(response2);
            return response;
        }
        else if(priceResponses.size() == 1){
            PriceResponse response = priceResponses.get(0);
            System.out.println("Price = " + response.getPrice());
            priceResponses.remove(priceResponses.get(0));
            return response;
        }
        else {
            return new PriceResponse(request.getProduct(), -1);
        }
    }

    private static final SupervisorStrategy strategy
            = new OneForOneStrategy(10, Duration.create("1 minute"), DeciderBuilder.
            matchAny(o -> (SupervisorStrategy.Directive) SupervisorStrategy.resume()).
            build());

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public void preStart() throws Exception {
        super.preStart();
        context().actorOf(Props.create(Store.class), "store1");
        context().actorOf(Props.create(Store.class), "store2");
    }

}


