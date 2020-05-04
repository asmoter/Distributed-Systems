#ifndef SMARTHOUSE_ICE
#define SMARTHOUSE_ICE

module SmartHouse {

	enum PowerMode { ON, OFF };
	enum LightMode { CLASSIC, MOTIONDETECTION, SUBDUED };
	enum CoffeeType { ESPRESSO, AMERICANO, FLATWHITE, CAPPUCCINO, LATTE };

    sequence<CoffeeType> CurrentlyAvailableCoffeeTypes;

	struct Time {
		short hour;
		short minute;
		short second;
	}

	exception DeviceError {
		Time timeOfError;
		string reason;
	}

	exception OutOfRangeExc extends DeviceError {}
	exception AlreadyTurnedOffExc extends DeviceError {}
	exception AlreadyTurnedOnExc extends DeviceError {}
	exception IsOffExc extends DeviceError {}
	exception LackOfIngredientExc extends DeviceError {}


	interface ISmartDevice {
		idempotent PowerMode getPowerMode();
		void turnOn() throws AlreadyTurnedOnExc;
		void turnOff() throws AlreadyTurnedOffExc;
	}

	interface AirConditioning extends ISmartDevice {
   		idempotent int getTemperature() throws IsOffExc;
   		void setTemperature(float temperature) throws OutOfRangeExc, IsOffExc;
   	}

	interface Alarm extends ISmartDevice {
		idempotent Time getStartTime() throws IsOffExc;
		void setStartTime(Time startTime) throws OutOfRangeExc, IsOffExc;
		idempotent Time getEndTime() throws IsOffExc;
		void setEndTime(Time endTime) throws OutOfRangeExc, IsOffExc;
	}

	interface Lamp extends ISmartDevice {
		idempotent LightMode getLightMode() throws IsOffExc;
		void setLightMode(LightMode mode) throws IsOffExc;
	}

   	interface CoffeeMaker extends ISmartDevice {
   		void makeCoffee(CoffeeType coffeeType) throws IsOffExc, LackOfIngredientExc;
		void addSugar(int spoons) throws LackOfIngredientExc, OutOfRangeExc, IsOffExc;
		idempotent CurrentlyAvailableCoffeeTypes getAvailableCoffeeTypes() throws IsOffExc;
	}
}

#endif