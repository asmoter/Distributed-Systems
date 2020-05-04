# slice2py --output-dir gen slice/smartHouse.ice

import sys
import traceback
import Ice
import datetime

# sys.path.insert(1, './gen')

Ice.loadSlice("../Slice/smartHouse.ice")
import SmartHouse


def generate_time():
    timestamp = datetime.datetime.now()
    return SmartHouse.Time(timestamp.hour, timestamp.minute, timestamp.second)


def check_if_off(power_mode):
    if power_mode == SmartHouse.PowerMode.OFF:
        error_time = generate_time()
        raise SmartHouse.IsOffExc(error_time, "Device is off. Turn it on first")


class SmartDeviceI(SmartHouse.ISmartDevice):
    def __init__(self):
        self.power_mode = SmartHouse.PowerMode.ON

    def getPowerMode(self, current=None):
        return self.power_mode

    def turnOn(self, current=None):
        if self.power_mode == SmartHouse.PowerMode.ON:
            error_time = generate_time()
            raise SmartHouse.AlreadyTurnedOnExc(error_time, "Device is already on")
        self.power_mode = SmartHouse.PowerMode.ON

    def turnOff(self, current=None):
        if self.power_mode == SmartHouse.PowerMode.OFF:
            error_time = generate_time()
            raise SmartHouse.AlreadyTurnedOnExc(error_time, "Device is already off")
        self.power_mode = SmartHouse.PowerMode.OFF


class AirConditioningI(SmartHouse.AirConditioning, SmartDeviceI):
    def __init__(self):
        super().__init__()
        self.temperature = 20

    def getTemperature(self, current=None):
        check_if_off(self.power_mode)
        return self.temperature

    def setTemperature(self, temperature, current=None):
        check_if_off(self.power_mode)
        if temperature not in range(16, 31):
            error_time = generate_time()
            raise SmartHouse.OutOfRangeExc(error_time, "Temperature out of range")
        self.temperature = temperature


class AlarmI(SmartHouse.Alarm, SmartDeviceI):
    def __init__(self):
        super().__init__()
        self.start_time = SmartHouse.Time(8, 0, 0)
        self.end_time = SmartHouse.Time(20, 0, 0)

    def getStartTime(self, current=None):
        check_if_off(self.power_mode)
        return self.start_time

    def setStartTime(self, time, current=None):
        check_if_off(self.power_mode)
        if time.hour not in range(0, 23) or time.minute not in range(0, 59) or time.second not in range(0, 59):
            error_time = generate_time()
            raise SmartHouse.OutOfRangeExc(error_time, "Time out of range")
        self.start_time = time

    def getEndTime(self, current=None):
        check_if_off(self.power_mode)
        return self.end_time

    def setEndTime(self, time, current=None):
        check_if_off(self.power_mode)
        if time.hour not in range(0, 23) or time.minute not in range(0, 59) or time.second not in range(0, 59):
            error_time = generate_time()
            raise SmartHouse.OutOfRangeExc(error_time, "Time out of range")
        self.end_time = time


class LampI(SmartHouse.Lamp, SmartDeviceI):
    def __init__(self):
        super().__init__()
        self.light_mode = SmartHouse.LightMode.CLASSIC

    def getLightMode(self, current=None):
        check_if_off(self.power_mode)
        return self.light_mode

    def setLightMode(self, light_mode, current=None):
        check_if_off(self.power_mode)
        self.light_mode = light_mode


class CoffeeMakerI(SmartHouse.CoffeeMaker, SmartDeviceI):
    def __init__(self):
        super().__init__()
        self.sugar = 50
        self.water = 400
        self.coffee = 400

    def makeCoffee(self, coffee_type, current=None):
        check_if_off(self.power_mode)
        if self.water - 150 < 0:
            error_time = generate_time()
            raise SmartHouse.LackOfIngredientExc(error_time, "Not enough water in machine")
        if self.coffee - 20 < 0:
            error_time = generate_time()
            raise SmartHouse.LackOfIngredientExc(error_time, "Not enough coffee in machine")
        self.water -= 150
        self.coffee -= 20

    def addSugar(self, spoons, current=None):
        check_if_off(self.power_mode)
        if spoons not in range(1, 5):
            error_time = generate_time()
            raise SmartHouse.OutOfRangeExc(error_time, "Choose between 1 and 5 spoons")
        if self.sugar - spoons < 0:
            error_time = generate_time()
            raise SmartHouse.LackOfIngredientExc(error_time, "Not enough sugar in machine")
        self.sugar -= spoons

    def getAvailableCoffeeTypes(self, current=None):
        check_if_off(self.power_mode)
        return [SmartHouse.CoffeeType.ESPRESSO, SmartHouse.CoffeeType.AMERICANO, SmartHouse.CoffeeType.FLATWHITE,
                SmartHouse.CoffeeType.CAPPUCCINO, SmartHouse.CoffeeType.LATTE]


class ServerServantLocator(Ice.ServantLocator):

    def __init__(self):
        self.servants = {}

    def locate(self, current):
        name = current.id.name
        if name in self.servants.keys():
            return self.servants[name]
        print('-> New servant: ' + name)
        servant = None
        if name == "airConditioning":
            servant = AirConditioningI()
        elif name == "alarm":
            servant = AlarmI()
        elif name == "lamp":
            servant = LampI()
        elif name == "coffeeMaker":
            servant = CoffeeMakerI()
        else:
            raise Ice.ObjectNotFoundException
        self.servants[name] = servant
        return servant

    def finished(self, current, servant, cookie):
        pass

    def deactivate(self, category):
        pass


with Ice.initialize(sys.argv) as communicator:
    try:
        adapter = communicator.createObjectAdapterWithEndpoints("SmartHouseAdapter", "default -p 10000")
        locator = ServerServantLocator()
        adapter.addServantLocator(locator, "")
        adapter.activate()
        print("Server initialized")
        print("Start event processing")
        communicator.waitForShutdown()
    except KeyboardInterrupt:
        print('Bye!')
    except:
        traceback.print_exc()
