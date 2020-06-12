package Actors;

import Messages.DatabaseResponse;
import Messages.PriceRequest;
import Messages.PriceResponse;
import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteOpenMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Server extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private int requestID = 0;
    private Map<Integer, List<PriceResponse>> Responses = new HashMap<>();
    private Map<String, Integer>  DatabaseResponses = new HashMap<>();

    private ActorRef dataBase = getContext().actorOf(Props.create(DbQueryHandler.class));

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(PriceRequest.class, request -> {
                    log.info("Received request from client" + request.getClientID() + ". What is the price of: " + request.getProduct());
                    ActorRef client = getSender();
                    request.setRequestID(requestID);

                    dataBase.tell(request, getSelf());
                    context().child("store1").get().tell(request, getSelf());
                    context().child("store2").get().tell(request, getSelf());

                    getContext().system().scheduler()
                            .scheduleOnce(Duration.ofMillis(500), () ->
                                            client.tell(returnQueryNumber(request), getSelf()),
                                    context().system().dispatcher());

                    getContext().system().scheduler()
                            .scheduleOnce(Duration.ofMillis(300), () ->
                                client.tell(returnPrice(request), getSelf()),
                                context().system().dispatcher());

                    requestID++;
                })
                .match(PriceResponse.class, response -> {
                    log.info("Received response for client" + response.getClientID() + " -> "+ response.getProduct() + ": " + response.getPrice());
                    addResponse(response);
                })
                .match(DatabaseResponse.class, response -> {
                    log.info("Received response from database. Query counter for " + response.getProduct() + " -> " + response.getQueryCounter());
                    updateDatabaseResponses(response);
                })
                .matchAny(o -> log.info("Server: received unknown message: " + o))
                .build();
    }

    private DatabaseResponse returnQueryNumber(PriceRequest request) {
        String productName = request.getProduct();
        if(DatabaseResponses.containsKey(productName)){
            int queryCounter = DatabaseResponses.get(productName);
            return new DatabaseResponse(productName, queryCounter);
        } else{
            return new DatabaseResponse(productName, -1);
        }
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

    private void updateDatabaseResponses(DatabaseResponse response){
        if(DatabaseResponses.containsKey(response.getProduct())){
            int currentQueryCounter = DatabaseResponses.get(response.getProduct());
            DatabaseResponses.put(response.getProduct(), ++currentQueryCounter);
        } else{
            DatabaseResponses.put(response.getProduct(), response.getQueryCounter());
        }
    }

    private PriceResponse returnPrice(PriceRequest request){

        if(Responses.containsKey(request.getRequestID())){
            List<PriceResponse> priceResponses = Responses.get(request.getRequestID());

            if(priceResponses.size() == 0){
                return new PriceResponse(request.getClientID(), request.getRequestID(), request.getProduct(), -1);
            }
            else if(priceResponses.size() == 1){
                PriceResponse response = priceResponses.get(0);
                return response;
            }
            else{
                if(priceResponses.get(0).getPrice() < priceResponses.get(1).getPrice()){
                    return priceResponses.get(0);
                } else{
                    return priceResponses.get(1);
                }
            }
        }
        else {
            return new PriceResponse(request.getClientID(), request.getRequestID(), request.getProduct(), -1);
        }
    }

    @Override
    public void preStart() throws Exception {

        context().actorOf(Props.create(Store.class), "store1");
        context().actorOf(Props.create(Store.class), "store2");

        Class.forName("org.sqlite.JDBC");
        SQLiteConfig config = new SQLiteConfig();
        config.setOpenMode(SQLiteOpenMode.FULLMUTEX);
        Connection connection = DriverManager.getConnection("jdbc:sqlite:ProductsDB.db", config.toProperties());

        Statement statement = connection.createStatement();
        String query = "create table if not exists Products " +
                "(ProductName varchar(256) primary key not null, " +
                "QueryCounter int not null)";

        statement.executeUpdate(query);
        statement.close();
        connection.close();

        log.info("Succesfully created database Products");
    }
}


