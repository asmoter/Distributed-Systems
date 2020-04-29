package grpc.server;

import gen.Event;
import gen.EventType;
import gen.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventGenerator {

    private Map<EventType, Event> map = new HashMap<EventType, Event>();

    private static String[] cities = new String[]{"Cracow", "Warsaw", "London", "Venice", "Paris"};

    private static String[] names = new String[]{"Juwemnalia", "Noc Muzeow", "Parada Smokow", "Festiwal Smakow", "Pokaz mody"};

    private static int[] fees = new int[]{0, 10, 30, 50, 100};

    private static int[] duration = new int[]{1, 2, 3, 5, 7};

    private static String[] types = new String[]{"CONCERT, FESTIVAL, EXHIBITION, PARADE"};

    private static String[] firstNames = new String[]{"Kate", "Michael", "Rose", "Phillip", "Thomas"};

    private static String[] lastNames = new String[]{"Kowalsky", "Smith", "Carols", "Bell", "Novak"};

    private static String[] phoneNumbers = new String[]{"669 134 782", "992 781 916", "600 789 221", "535 500 823", "325 678 910"};


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
        int r = (int) (Math.random() * (max -min));
        System.out.println(r);
        return r;
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

    private static String generateLasttName(){
        int min = 0, max = 5;
        int i = generateRandom(min, max);
        return lastNames[i];
    }

    private static String generatePhoneNumber(){
        int min = 0, max = 5;
        int i = generateRandom(min, max);
        return phoneNumbers[i];
    }

    private static List<Person> setRandomOrganizers(){
        int n = generateRandom(1, 3);
        List<Person> organizers = new ArrayList<Person>();
        for(int i = 0; i < n; i++){
            organizers.add(Person.newBuilder()
                    .setFirstName(generateFirstName())
                    .setLastName(generateLasttName())
                    .setPhoneNumber(generatePhoneNumber())
                    .build());
        }
        return organizers;
    }
}
