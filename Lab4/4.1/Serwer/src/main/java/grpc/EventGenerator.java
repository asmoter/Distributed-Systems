package grpc;

import gen.Event;
import gen.EventType;
import gen.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class EventGenerator {

    int timeToWait = 5000;
    private List<Event> events = new ArrayList<Event>();
    private ReentrantLock lock;

    public ReentrantLock getLock() {
        return lock;
    }

    public EventGenerator(ReentrantLock lock) {
        this.lock = lock;
    }

    private static String[] cities = new String[]{"Cracow", "Warsaw", "London", "Venice", "Paris", "New York"};

    private static String[] names = new String[]{"Juwenalia", "Noc Muzeow", "Parada Smokow", "Festiwal Smakow", "Pokaz mody", "Noc Naukowcow"};

    private static int[] fees = new int[]{0, 10, 15,  30, 50, 100};

    private static int[] duration = new int[]{1, 2, 3, 5, 7, 10};

    private static String[] firstNames = new String[]{"Kate", "Michael", "Rose", "Phillip", "Thomas", "Elisa"};

    private static String[] lastNames = new String[]{"Kowalsky", "Smith", "Carols", "Bell", "Novak", "Rascal"};

    private static String[] phoneNumbers = new String[]{"669 134 782", "992 781 916", "600 789 221", "535 500 823", "325 678 910"};

    public List<Event> getEvents(){
        return events;
    }

    public void generateEvents(){
        while(!Thread.currentThread().isInterrupted()){
            try {
                Event event = generateEvent();
                System.out.println("New event! " + event.getEventType() + ": " +
                        event.getName() + " in " + event.getCity());
                lock.lock();
                events.add(event);
                lock.unlock();
                Thread.sleep(timeToWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    Event generateEvent(){
        return Event.newBuilder()
                .setName(generateName())
                .setEventType(generateType())
                .setCity(generateCity())
                .setEntranceFee(generateFee())
                .setDuration(generateDuration())
                .addAllOrganizer(setRandomOrganizers())
                .build();
    }

    private static int generateRandom(int min, int max){
        return (int) (Math.random() * (max -min + 1));
    }

    private static String generateCity(){
        int min = 0, max = 5;
        int i = generateRandom(min, max);
        return cities[i];
    }

    private static String generateName(){
        int min = 0, max = 5;
        int i = generateRandom(min, max);
        return names[i];
    }

    private static int generateFee(){
        int min = 0, max = 5;
        int i = generateRandom(min, max);
        return fees[i];
    }

    private static int generateDuration(){
        int min = 0, max = 5;
        int i = generateRandom(min, max);
        return duration[i];
    }

    private static EventType generateType(){
        int min = 0, max = 3;
        int i = generateRandom(min, max);
        return EventType.forNumber(i);
    }

    private static String generateFirstName(){
        int min = 0, max = 5;
        int i = generateRandom(min, max);
        return firstNames[i];
    }

    private static String generateLastName(){
        int min = 0, max = 5;
        int i = generateRandom(min, max);
        return lastNames[i];
    }

    private static String generatePhoneNumber(){
        int min = 0, max = 4;
        int i = generateRandom(min, max);
        return phoneNumbers[i];
    }

    private static List<Person> setRandomOrganizers(){
        int n = generateRandom(1, 4);
        List<Person> organizers = new ArrayList<Person>();
        for(int i = 0; i < n; i++){
            organizers.add(Person.newBuilder()
                    .setFirstName(generateFirstName())
                    .setLastName(generateLastName())
                    .setPhoneNumber(generatePhoneNumber())
                    .build());
        }
        return organizers;
    }
}