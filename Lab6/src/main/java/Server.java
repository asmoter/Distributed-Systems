import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.DeciderBuilder;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Server extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private int requestID = 0;
    Map<String, ActorRef> clients = new HashMap<>();
    Map<Integer, PriceRequest> Requests = new HashMap<>();
    Map<Integer, List<PriceResponse>> Responses = new HashMap<>();

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(PriceRequest.class, request -> {
                    log.info("Received request from client" + request.getClientID() + ". What is the price of: " + request.getProduct());
                    ActorRef client = getSender();
                    request.setRequestID(requestID);
                    clients.put(request.getClientID(), client);
//                    Requests.put(requestID, request);

                    context().child("store1").get().tell(request, getSelf());
                    context().child("store2").get().tell(request, getSelf());

                    getContext().system().scheduler()
                            .scheduleOnce(java.time.Duration.ofMillis(300), () ->
                                client.tell(returnPrice(request), getSelf()),
                                context().system().dispatcher());

                    requestID++;
                })
                .match(PriceResponse.class, response -> {
                    log.info("Received response for client" + response.getClientID() + " -> "+ response.getProduct() + ": " + response.getPrice());
                    addResponse(response);
                })
                .matchAny(o -> log.info("Server: received unknown message: " + o))
                .build();
    }

    private void addResponse(PriceResponse response){
        List<PriceResponse> responses;
        if(Responses.containsKey(response.getRequestID())){
            responses = Responses.get(response.getRequestID());
        } else {
            responses = new ArrayList<PriceResponse>();
        }
        responses.add(response);
        Responses.put(response.getRequestID(), responses);
    }

    private PriceResponse returnPrice(PriceRequest request){

        List<PriceResponse> priceResponses = Responses.get(request.getRequestID());

        if(priceResponses.size() == 0){
            return new PriceResponse(request.getClientID(), request.getRequestID(), request.getProduct(), -1);
        }
        else if(priceResponses.size() == 1){
            PriceResponse response = priceResponses.get(0);
            System.out.println("Price = " + response.getPrice());
            return response;
        }
        else{
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
            return response;
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


