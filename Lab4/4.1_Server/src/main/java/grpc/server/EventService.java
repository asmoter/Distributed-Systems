package grpc.server;

import gen.*;
import io.grpc.stub.StreamObserver;


import java.util.*;

public class EventService extends EventSubscriptionGrpc.EventSubscriptionImplBase {

    private Map<StreamObserver<Event>, List<EventType>> subscriptions = new HashMap<StreamObserver<Event>, List<EventType>>();
    //    private List<Event> events = new ArrayList<Event>();
    EventGenerator generator;


    public EventService(EventGenerator generator) {
        this.generator = generator;
    }

    @Override
    public void subscribeOnEvent(EventRequest request, StreamObserver<Event> responseObserver) {

        List<EventType> eventTypes = new ArrayList<EventType>() {
        };
        List<Event> announcedEvents = new ArrayList<Event>() {
        };
//        List<Event> events = generator.getEvents();

        if (subscriptions.containsKey(responseObserver)) {
            eventTypes = subscriptions.get(responseObserver);
            eventTypes.add(request.getEventType());
        } else {
            System.out.println("\n== New subscriber: " + request.getSubscriber() + " subscribing on " + request.getEventType() + " ==\n");
            eventTypes.add(request.getEventType());
        }
        subscriptions.put(responseObserver, eventTypes);

        EventType eventType = request.getEventType();

        while (true) {

            List<Event> events = generator.getEvents();
            for (Event e : events) {
                if (!announcedEvents.contains(e)) {
                    if (e.getEventType().equals(eventType)) {
                        responseObserver.onNext(e);
                        announcedEvents.add(e);
                    }
                }
            }
        }

//        while(true){
//
//            for(Event e: events){
//                if(!announcedEvents.contains(e)){
//                    if(e.getEventType().equals(eventType)){
//                        responseObserver.onNext(e);
//                        announcedEvents.add(e);
//                    }
//                }
//            }
//            events = generator.getEvents();
//        }
    }
}


//    public void generateEvents(){
//        while(!Thread.currentThread().isInterrupted()){
//            try {
//                Event event = generator.generateEvent();
//                System.out.println("New event! " + event.getEventType() + ": " +
//                        event.getName() + " in " + event.getCity());
//                events.add(event);
//                Thread.sleep(timeToWait);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

