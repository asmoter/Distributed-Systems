import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.util.Random;

public class Store extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(PriceRequest.class, request -> {
                    int timeToSleep = new Random().nextInt(400) + 100;
                    Thread.sleep(timeToSleep);
                    log.info("Time to sleep: " + timeToSleep + " [ms]");
                    int value = new Random().nextInt(10) + 1;
                    PriceResponse response = new PriceResponse(request.getProduct(), value);
                    getSender().tell(response, getSelf());
                })
                .matchAny(o -> log.info("Strore: received unknown message: " + o))
                .build();
    }

}