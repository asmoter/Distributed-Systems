package Actors;

import Messages.DatabaseResponse;
import Messages.PriceRequest;
import Messages.PriceResponse;
import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Server extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private int requestID = 0;
    Map<Integer, List<PriceResponse>> Responses = new HashMap<>();
    Map<String, Integer>  DatabaseResponses = new HashMap<>();

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(PriceRequest.class, request -> {
                    log.info("Received request from client" + request.getClientID() + ". What is the price of: " + request.getProduct());
                    ActorRef client = getSender();
                    request.setRequestID(requestID);

                    context().child("store1").get().tell(request, getSelf());
                    context().child("store2").get().tell(request, getSelf());
                    context().child("dataBase").get().tell(request, getSelf());

                    getContext().system().scheduler()
                            .scheduleOnce(Duration.ofMillis(300), () ->
                                client.tell(returnPrice(request), getSelf()),
                                context().system().dispatcher());

                    requestID++;
                    System.out.println("Current query counter: " + request.getProduct() + " -> " + DatabaseResponses.get(request.getProduct()));
                })
                .match(PriceResponse.class, response -> {
                    log.info("Received response for client" + response.getClientID() + " -> "+ response.getProduct() + ": " + response.getPrice());
                    addResponse(response);
                })
                .match(DatabaseResponse.class, response -> {
                    log.info("Received response from database. Query counter for " + response.getProduct() + " -> " + response.getQueryCounter());
                    addDatabaseResponse(response);
                })
                .matchAny(o -> log.info("Actors.Server: received unknown message: " + o))
                .build();
    }

    private void addResponse(PriceResponse response){
        List<PriceResponse> responses;
        if(Responses.containsKey(response.getRequestID())){
            responses = Responses.get(response.getRequestID());
        } else {
            responses = new ArrayList<>();
        }
        responses.add(response);
        Responses.put(response.getRequestID(), responses);
    }

    private void addDatabaseResponse(DatabaseResponse response){
        if(DatabaseResponses.containsKey(response.getProduct())){
            int currentQueryCounter = DatabaseResponses.get(response.getProduct());
            DatabaseResponses.put(response.getProduct(), ++currentQueryCounter);
        } else{
            DatabaseResponses.put(response.getProduct(), 1);
        }
    }

    private PriceResponse returnPrice(PriceRequest request){
        System.out.println("~~~ Inside returnPrice for client: " + request.getClientID() + " ->  " + request.getProduct() + " request ID =  " + request.getRequestID() + "  ~~~");

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
            if(priceResponses.get(0).getPrice() < priceResponses.get(1).getPrice()){
                System.out.println("Price = " + priceResponses.get(0).getPrice());
                return priceResponses.get(0);
            } else{
                System.out.println("Price = " + priceResponses.get(1).getPrice());
                return priceResponses.get(1);
            }
        }
    }

    @Override
    public void preStart() throws Exception {

        

        super.preStart();
        context().actorOf(Props.create(Store.class), "store1");
        context().actorOf(Props.create(Store.class), "store2");
        context().actorOf(Props.create(DbHandler.class), "dataBase");
    }
}


