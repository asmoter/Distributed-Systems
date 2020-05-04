package Client;

import SmartHouse.*;
import com.zeroc.Ice.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Exception;


// slice2java --output-dir Client/gen Slice/smartHouse.ice

enum Device {AirConditioning, Alarm, Lamp, CoffeeMaker, ChooseDevice};
enum ACAction {GetPowerMode, TurnOn, TurnOff, GetTemp, SetTemp, ChangeDevice };
enum AlarmAction {GetPowerMode, TurnOn, TurnOff, GetStartTime, SetStartTime, GetEndTime, SetEndTime, ChangeDevice };
enum LampAction {GetPowerMode, TurnOn, TurnOff, GetLightMode, SetLightMode, ChangeDevice };
enum CMAction {GetPowerMode, TurnOn, TurnOff, MakeCoffee, AddSugar, GetAvailableCoffeeTypes, ChangeDevice };

public class Client {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args){

        int status = 0;
        Communicator communicator = null;

        try{
            communicator = Util.initialize(args);
            ObjectPrx ACObject = communicator.stringToProxy("airConditioning:default -p 10000");
            ObjectPrx alarmObject = communicator.stringToProxy("alarm:default -p 10000");
            ObjectPrx lampObject = communicator.stringToProxy("lamp:default -p 10000");
            ObjectPrx coffeeMakerObject = communicator.stringToProxy("coffeeMaker:default -p 10000");

            AirConditioningPrx airConditioning = null;
            AlarmPrx alarm = null;
            LampPrx lamp = null;
            CoffeeMakerPrx coffeeMaker = null;

            Device device = Device.ChooseDevice;
            while(true){
                try{
                    while(device == Device.ChooseDevice){
                        device = chooseDevice();
                    }
                    while(device == Device.AirConditioning){
                        ACAction action;
                        if(airConditioning == null){
                            airConditioning = AirConditioningPrx.checkedCast(ACObject);
                            if(airConditioning == null){
                                throw new java.lang.Error("Invalid proxy");
                            }
                        }
                        action = chooseAirConditioningAction();
                        if(action == ACAction.ChangeDevice) device = Device.ChooseDevice;
                        else performACAction(airConditioning, action);
                    }

                    while(device == Device.Alarm){
                        AlarmAction action;
                        if(alarm == null){
                            alarm = AlarmPrx.checkedCast(alarmObject);
                            if(alarm == null){
                                throw new java.lang.Error("Invalid proxy");
                            }
                        }
                        action = chooseAlarmAction();
                        if(action == AlarmAction.ChangeDevice) device = Device.ChooseDevice;
                        else performAlarmAction(alarm, action);
                    }

                    while(device == Device.Lamp){
                        LampAction action;
                        if(lamp == null){
                            lamp = LampPrx.checkedCast(lampObject);
                            if(lamp == null){
                                throw new java.lang.Error("Invalid proxy");
                            }
                        }
                        action = chooseLampAction();
                        if(action == LampAction.ChangeDevice) device = Device.ChooseDevice;
                        else performLampAction(lamp, action);
                    }

                    while(device == Device.CoffeeMaker){
                        CMAction action;
                        if(coffeeMaker == null){
                            coffeeMaker = CoffeeMakerPrx.checkedCast(coffeeMakerObject);
                            if(coffeeMaker == null){
                                throw new java.lang.Error("Invalid proxy");
                            }
                        }
                        action = chooseCoffeeMakerAction();
                        if(action == CMAction.ChangeDevice) device = Device.ChooseDevice;
                        else performCoffeeMakerAction(coffeeMaker, action);
                    }
                }
                catch (java.io.IOException e) {
                    System.err.println(e);
                }
            }
        } catch (LocalException e) {
            e.printStackTrace();
            status = 1;
        } catch (Exception e) {
            System.err.println(e);
            status = 1;
        }
        if (communicator != null) {
            try {
                communicator.destroy();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                status = 1;
            }
        }
        System.exit(status);
    }

    private static Device chooseDevice() throws IOException {
        System.out.println("\nChoose one of the available devices. Enter: \n" +
                "-> AC - for air conditioning \n" +
                "-> A - for alarm \n" +
                "-> L - for lamp \n" +
                "-> CM - for coffee maker");
        String device = br.readLine();
        switch (device) {
            case "AC":
                return Device.AirConditioning;
            case "A":
                return Device.Alarm;
            case "L":
                return Device.Lamp;
            case "CM":
                return Device.CoffeeMaker;
            default:
                return Device.ChooseDevice;
        }
    }

    private static ACAction chooseAirConditioningAction() throws IOException {
        System.out.println("\nChoose one of the available AC actions. Enter: \n" +
                "-> Power - to get current power mode \n" +
                "-> On - to turn on the air conditioning \n" +
                "-> Off - to turn off the air conditioning \n" +
                "-> Get - to get current temperature \n" +
                "-> Set - to set temperature \n" +
                "-> Device - to change device");
        String action = br.readLine();
        switch (action) {
            case "Power":
                return ACAction.GetPowerMode;
            case "On":
                return ACAction.TurnOn;
            case "Off":
                return ACAction.TurnOff;
            case "Get":
                return ACAction.GetTemp;
            case "Set":
                return ACAction.SetTemp;
            case "Device":
                return ACAction.ChangeDevice;
            default:
                throw new IOException("Incorrect action type");
        }
    }

    private static AlarmAction chooseAlarmAction() throws IOException {
        System.out.println("\nChoose one of the available alarm actions. Enter: \n" +
                "-> Power - to get current power mode \n" +
                "-> On - to turn on the alarm \n" +
                "-> Off - to turn off the alarm \n" +
                "-> GetStart - to get the time when alarm will turn on \n" +
                "-> SetStart - to set the time when alarm will turn on \n" +
                "-> GetEnd - to get the time when alarm will turn off \n" +
                "-> SetEnd - to set the time when alarm will turn off \n" +
                "-> Device - to change device");
        String action = br.readLine();
        switch (action) {
            case "Power":
                return AlarmAction.GetPowerMode;
            case "On":
                return AlarmAction.TurnOn;
            case "Off":
                return AlarmAction.TurnOff;
            case "GetStart":
                return AlarmAction.GetStartTime;
            case "SetStart":
                return AlarmAction.SetStartTime;
            case "GetEnd":
                return AlarmAction.GetEndTime;
            case "SetEnd":
                return AlarmAction.SetEndTime;
            case "Device":
                return AlarmAction.ChangeDevice;
            default:
                throw new IOException("Incorrect action type");
        }
    }

    private static LampAction chooseLampAction() throws IOException {
        System.out.println("\nChoose one of the available lamp actions. Enter: \n" +
                "-> Power - to get current power mode \n" +
                "-> On - to turn on the lamp \n" +
                "-> Off - to turn off the lamp \n" +
                "-> Get - to get current light mode \n" +
                "-> Set - to set the light mode \n" +
                "-> Device - to change device");
        String action = br.readLine();
        switch (action) {
            case "Power":
                return LampAction.GetPowerMode;
            case "On":
                return LampAction.TurnOn;
            case "Off":
                return LampAction.TurnOff;
            case "Get":
                return LampAction.GetLightMode;
            case "Set":
                return LampAction.SetLightMode;
            case "Device":
                return LampAction.ChangeDevice;
            default:
                throw new IOException("Incorrect action type");
        }
    }

    private static CMAction chooseCoffeeMakerAction() throws IOException {
        System.out.println("\nChoose one of the available coffee maker actions. Enter: \n" +
                "-> Power - to get current power mode \n" +
                "-> On - to turn on the lamp \n" +
                "-> Off - to turn off the lamp \n" +
                "-> Make - to make coffee \n" +
                "-> Add - to add sugar \n" +
                "-> Get - to get currently available coffee types \n" +
                "-> Device - to change device");
        String action = br.readLine();
        switch (action) {
            case "Power":
                return CMAction.GetPowerMode;
            case "On":
                return CMAction.TurnOn;
            case "Off":
                return CMAction.TurnOff;
            case "Make":
                return CMAction.MakeCoffee;
            case "Add":
                return CMAction.AddSugar;
            case "Get":
                return CMAction.GetAvailableCoffeeTypes;
            case "Device":
                return CMAction.ChangeDevice;
            default:
                throw new IOException("Incorrect action type");
        }
    }

    private static void performACAction(AirConditioningPrx airConditioning, ACAction action) throws IOException {
        switch (action){
            case ChangeDevice:
                break;
            case GetPowerMode:
                PowerMode mode = airConditioning.getPowerMode();
                System.out.println("\nAC power mode: " + mode);
                break;
            case TurnOn:
                try{
                    airConditioning.turnOn();
                    System.out.println("\nAir conditioning turned on!");
                } catch (AlreadyTurnedOnExc e){
                    System.out.println(e.reason);
                }
                break;
            case TurnOff:
                try{
                    airConditioning.turnOff();
                    System.out.println("\nAir conditioning turned off!");
                } catch (AlreadyTurnedOffExc e){
                    returnError(e);
                }
                break;
            case GetTemp:
                try {
                    float temp = airConditioning.getTemperature();
                    System.out.println("\nAC set to " + temp + " degrees");
                } catch (IsOffExc e) {
                    returnError(e);
                }
                break;
            case SetTemp:
                System.out.println("\nSet AC temperature. Enter temperature value from 16 to 30 degrees:");
                float temp = Float.parseFloat(br.readLine());
                try {
                    airConditioning.setTemperature(temp);
                    System.out.println("\nSet AC temperature to " + temp + " degrees");
                } catch (IsOffExc | OutOfRangeExc e) {
                    returnError(e);
                }
                break;
        }
    }

    private static void performAlarmAction(AlarmPrx alarm, AlarmAction action) throws IOException {
        switch (action){
            case ChangeDevice:

                break;
            case GetPowerMode:
                PowerMode mode = alarm.getPowerMode();
                System.out.println("\nAlarm power mode: " + mode);
                break;
            case TurnOn:
                try{
                    alarm.turnOn();
                    System.out.println("\nAlarm turned on!");
                } catch (AlreadyTurnedOnExc e){
                    System.out.println(e.reason);
                }
                break;
            case TurnOff:
                try{
                    alarm.turnOff();
                    System.out.println("\nAlarm turned off!");
                } catch (AlreadyTurnedOffExc e){
                    returnError(e);
                }
                break;
            case GetStartTime:
                try {
                    SmartHouse.Time startTime = alarm.getStartTime();
                    System.out.println("\nAlarm will arm at " + returnTime(startTime));
                } catch (IsOffExc e) {
                    returnError(e);
                }
                break;
            case SetStartTime:
                System.out.println("\nSet alarm arming time. Enter time value [h m s]:");
                SmartHouse.Time startTime = parseTime(br.readLine());
                try {
                    alarm.setStartTime(startTime);
                    System.out.println("\nSet arming time to " + returnTime(startTime));
                } catch (IsOffExc | OutOfRangeExc e) {
                    returnError(e);
                }
                break;
            case GetEndTime:
                try {
                    SmartHouse.Time endTime = alarm.getEndTime();
                    System.out.println("\nAlarm will disarm at " + returnTime(endTime));
                } catch (IsOffExc e) {
                    returnError(e);
                }
                break;
            case SetEndTime:
                System.out.println("\nSet alarm disarming time. Enter time value [h m s]:");
                SmartHouse.Time endTime = parseTime(br.readLine());
                try {
                    alarm.setEndTime(endTime);
                    System.out.println("\nSet disarming time to " + returnTime(endTime));
                } catch (IsOffExc | OutOfRangeExc e) {
                    returnError(e);
                }
                break;
        }
    }

    private static void performLampAction(LampPrx lamp, LampAction action) throws Exception {
        switch (action){
            case ChangeDevice:
                break;
            case GetPowerMode:
                PowerMode mode = lamp.getPowerMode();
                System.out.println("\nLamp power mode: " + mode);
                break;
            case TurnOn:
                try{
                    lamp.turnOn();
                    System.out.println("\nLamp turned on!");
                } catch (AlreadyTurnedOnExc e){
                    System.out.println(e.reason);
                }
                break;
            case TurnOff:
                try{
                    lamp.turnOff();
                    System.out.println("\nLamp turned off!");
                } catch (AlreadyTurnedOffExc e){
                    returnError(e);
                }
                break;
            case GetLightMode:
                try {
                    SmartHouse.LightMode lightMode = lamp.getLightMode();
                    System.out.println("\nLamp is set to " + lightMode + " mode");
                } catch (IsOffExc e) {
                    returnError(e);
                }
                break;
            case SetLightMode:
                SmartHouse.LightMode lightMode = chooseLightMode();
                try {
                    lamp.setLightMode(lightMode);
                    System.out.println("\nSet light mode to " + lightMode);
                } catch (IsOffExc e) {
                    returnError(e);
                }
                break;
        }
    }

    private static void performCoffeeMakerAction(CoffeeMakerPrx coffeeMaker, CMAction action) throws Exception {
        switch (action){
            case ChangeDevice:
                break;
            case GetPowerMode:
                PowerMode mode = coffeeMaker.getPowerMode();
                System.out.println("\nCoffee maker power mode: " + mode);
                break;
            case TurnOn:
                try{
                    coffeeMaker.turnOn();
                    System.out.println("\nCoffee maker turned on!");
                } catch (AlreadyTurnedOnExc e){
                    System.out.println(e.reason);
                }
                break;
            case TurnOff:
                try{
                    coffeeMaker.turnOff();
                    System.out.println("\nCoffee maker turned off!");
                } catch (AlreadyTurnedOffExc e){
                    returnError(e);
                }
                break;
            case MakeCoffee:
                SmartHouse.CoffeeType coffeeType = chooseCoffeeType();
                try {
                    coffeeMaker.makeCoffee(coffeeType);
                    System.out.println("\nCoffee is ready!");
                } catch (IsOffExc | LackOfIngredientExc e) {
                    returnError(e);
                }
                break;
            case AddSugar:
                System.out.println("\nHow much sugar you'd like? Enter number of spoons:");
                int spoons = Integer.parseInt(br.readLine());
                try {
                    coffeeMaker.addSugar(spoons);
                    System.out.println("\nAdded " + spoons + " spoons of sugar");
                } catch (IsOffExc | LackOfIngredientExc | OutOfRangeExc e) {
                    returnError(e);
                }
                break;
            case GetAvailableCoffeeTypes:
                try {
                    CoffeeType[] coffeeTypes = coffeeMaker.getAvailableCoffeeTypes();
                    System.out.println("\nCurrently available coffee types: ");
                    for(CoffeeType type: coffeeTypes){
                        System.out.print(type + ", ");
                    }
                    System.out.println(" Done!");
                } catch (IsOffExc e) {
                    returnError(e);
                }
                break;
        }
    }

    private static void returnError(SmartHouse.DeviceError error){
        System.out.println("Error occured! \n" + returnTime(error.timeOfError) + " -> " + error.reason);
    }

    private static String returnTime(SmartHouse.Time time){
        return  time.hour +  "h " + time.minute + "m " + time.second + "s ";
    }

    private static SmartHouse.Time parseTime(String timeString){
        String[] timeArgs = timeString.split(" ");
        SmartHouse.Time time = new Time();
        time.hour = Short.parseShort(timeArgs[0]);
        time.minute = Short.parseShort(timeArgs[1]);
        time.second = Short.parseShort(timeArgs[2]);
        return time;
    }

    private static SmartHouse.LightMode chooseLightMode() throws Exception {
        System.out.println("\nSet lamp light mode. Enter one of the following modes: \n" +
                "-> CLASSIC - for constant light \n" +
                "-> MOTION - for light activated with motion detection sensor \n" +
                "-> SUBDUED - for 50% light intensity \n");
        String mode = br.readLine();
        switch (mode){
            case "CLASSIC":
                return LightMode.CLASSIC;
            case "MOTION":
                return LightMode.MOTIONDETECTION;
            case "SUBDUED":
                return LightMode.SUBDUED;
            default:
                throw new Exception("Incorrect mode");
        }
    }

    private static CoffeeType chooseCoffeeType() throws Exception {
        System.out.println("\nMake coffee. Enter one of the following types of coffee: \n" +
                "-> espresso \n-> americano \n-> flat white \n-> cappuccino \n-> latte");
        String coffeeType = br.readLine();
        switch (coffeeType){
            case "espresso":
                return CoffeeType.ESPRESSO;
            case "americano":
                return CoffeeType.AMERICANO;
            case "flat white":
                return CoffeeType.FLATWHITE;
            case "cappuccino":
                return CoffeeType.CAPPUCCINO;
            case "latte":
                return CoffeeType.LATTE;
            default:
                throw new Exception("Incorrect coffee type");
        }
    }
}
