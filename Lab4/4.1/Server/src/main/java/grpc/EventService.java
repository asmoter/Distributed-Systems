package grpc;

import gen.*;
import io.grpc.stub.StreamObserver;

import java.util.*;

public class EventService extends EventSubscriptionGrpc.EventSubscriptionImplBase {

    private Map<StreamObserver<Event>, List<EventType>> subscriptions = new HashMap<StreamObserver<Event>, List<EventType>>();
    EventGenerator generator;


    public EventService(EventGenerator generator) {
        this.generator = generator;
    }

    @Override
    public void subscribeOnEvent(EventRequest request, StreamObserver<Event> responseObserver) {

        List<EventType> eventTypes = new ArrayList<EventType>() {};
        List<Event> announcedEvents = new ArrayList<Event>() {};
        List<Event> events;

        if (subscriptions.containsKey(responseObserver)) {
            eventTypes = subscriptions.get(responseObserver);
            eventTypes.add(request.getEventType());
        } else {
            System.out.println("\n== New subscriber! Subscribing on " + request.getEventType() + " ==\n");
            eventTypes.add(request.getEventType());
        }
        subscriptions.put(responseObserver, eventTypes);

        EventType eventType = request.getEventType();

        while (true) {
            generator.getLock().lock();
            events = generator.getEvents();
            for (Event e : events) {
                if (!announcedEvents.contains(e)) {
                    if (e.getEventType().equals(eventType)) {
                        responseObserver.onNext(e);
                        announcedEvents.add(e);
                    }
                }
            }
            generator.getLock().unlock();
        }
    }

    @Override
    public void connectToServer(Connect request, StreamObserver<Connect> responseObserver){
        System.out.println("Connection request");
        responseObserver.onNext(request);
        responseObserver.onCompleted();
    }
}
