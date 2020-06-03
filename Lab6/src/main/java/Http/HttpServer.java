package Http;

import Messages.PriceRequest;
import Messages.PriceResponse;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.server.HttpApp;
import akka.http.javadsl.server.PathMatchers;
import akka.http.javadsl.server.Route;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import org.jsoup.Jsoup;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static akka.pattern.Patterns.ask;

public class HttpServer extends HttpApp {

    private final ActorRef server;
    private final ActorSystem actorSystem;
    private final Materializer materializer;
    private Duration timeoutDuration = Duration.create(500, TimeUnit.MILLISECONDS);


    public HttpServer(ActorSystem system, ActorRef ref){
        this.actorSystem = system;
        this.server = ref;
        this.materializer = ActorMaterializer.create(system);
    }


    @Override
    public Route routes(){
        return path(PathMatchers.segment("price")
                .slash(PathMatchers.segment()), this::getPrice)
                .orElse(path(PathMatchers.segment("review")
                        .slash(PathMatchers.segment()), this::getReview));
    }


    private Route getPrice(String productName){

        return get(() -> {
            PriceRequest request = new PriceRequest("1", 1, productName);
            Future<Object> resultFuture = ask(server, request, 500);
            String price = null;
            PriceResponse response = null;
            String result = null;
            try {
                response = (PriceResponse) Await.result(resultFuture, timeoutDuration);
                price = String.valueOf(response.getPrice());
                result = "Price of " + productName + ": " + price;

            } catch (InterruptedException | TimeoutException e) {
                e.printStackTrace();
            }

            assert response != null;
            return complete(StatusCodes.OK, result, Jackson.marshaller());
        });
    }


    private Route getReview(String product){
        CompletionStage<Object> result = Http
                .get(actorSystem)
                .singleRequest(HttpRequest.create(getProductURL(product)))
                .thenCompose(response -> response.entity().toStrict(5000, materializer)
                        .thenApply(entity -> entity.getData().utf8String())
                        .thenApply(this::parseReview)
                );

        return onSuccess(result, res -> complete(StatusCodes.OK, res.toString(), Jackson.marshaller()));
    }


    private String getProductURL(String product){
        return String.format("https://www.opineo.pl/?szukaj=%s&s=2", product);
    }


    private String parseReview(String rawHtml){
        return  Jsoup.parse(rawHtml).body()
                .select("#screen > div:eq(3) > div:eq(0) > div:eq(2) > ul > li")
                .eachText().toString();
    }
}