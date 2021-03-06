# -*- coding: utf-8 -*-
#
# Copyright (c) ZeroC, Inc. All rights reserved.
#
#
# Ice version 3.7.3
#
# <auto-generated>
#
# Generated from file `smartHouse.ice'
#
# Warning: do not edit this file.
#
# </auto-generated>
#

from sys import version_info as _version_info_
import Ice, IcePy

# Start of module SmartHouse
_M_SmartHouse = Ice.openModule('SmartHouse')
__name__ = 'SmartHouse'

if 'PowerMode' not in _M_SmartHouse.__dict__:
    _M_SmartHouse.PowerMode = Ice.createTempClass()
    class PowerMode(Ice.EnumBase):

        def __init__(self, _n, _v):
            Ice.EnumBase.__init__(self, _n, _v)

        def valueOf(self, _n):
            if _n in self._enumerators:
                return self._enumerators[_n]
            return None
        valueOf = classmethod(valueOf)

    PowerMode.ON = PowerMode("ON", 0)
    PowerMode.OFF = PowerMode("OFF", 1)
    PowerMode._enumerators = { 0:PowerMode.ON, 1:PowerMode.OFF }

    _M_SmartHouse._t_PowerMode = IcePy.defineEnum('::SmartHouse::PowerMode', PowerMode, (), PowerMode._enumerators)

    _M_SmartHouse.PowerMode = PowerMode
    del PowerMode

if 'LightMode' not in _M_SmartHouse.__dict__:
    _M_SmartHouse.LightMode = Ice.createTempClass()
    class LightMode(Ice.EnumBase):

        def __init__(self, _n, _v):
            Ice.EnumBase.__init__(self, _n, _v)

        def valueOf(self, _n):
            if _n in self._enumerators:
                return self._enumerators[_n]
            return None
        valueOf = classmethod(valueOf)

    LightMode.CLASSIC = LightMode("CLASSIC", 0)
    LightMode.MOTIONDETECTION = LightMode("MOTIONDETECTION", 1)
    LightMode.SUBDUED = LightMode("SUBDUED", 2)
    LightMode._enumerators = { 0:LightMode.CLASSIC, 1:LightMode.MOTIONDETECTION, 2:LightMode.SUBDUED }

    _M_SmartHouse._t_LightMode = IcePy.defineEnum('::SmartHouse::LightMode', LightMode, (), LightMode._enumerators)

    _M_SmartHouse.LightMode = LightMode
    del LightMode

if 'CoffeeType' not in _M_SmartHouse.__dict__:
    _M_SmartHouse.CoffeeType = Ice.createTempClass()
    class CoffeeType(Ice.EnumBase):

        def __init__(self, _n, _v):
            Ice.EnumBase.__init__(self, _n, _v)

        def valueOf(self, _n):
            if _n in self._enumerators:
                return self._enumerators[_n]
            return None
        valueOf = classmethod(valueOf)

    CoffeeType.ESPRESSO = CoffeeType("ESPRESSO", 0)
    CoffeeType.AMERICANO = CoffeeType("AMERICANO", 1)
    CoffeeType.FLATWHITE = CoffeeType("FLATWHITE", 2)
    CoffeeType.CAPPUCCINO = CoffeeType("CAPPUCCINO", 3)
    CoffeeType.LATTE = CoffeeType("LATTE", 4)
    CoffeeType._enumerators = { 0:CoffeeType.ESPRESSO, 1:CoffeeType.AMERICANO, 2:CoffeeType.FLATWHITE, 3:CoffeeType.CAPPUCCINO, 4:CoffeeType.LATTE }

    _M_SmartHouse._t_CoffeeType = IcePy.defineEnum('::SmartHouse::CoffeeType', CoffeeType, (), CoffeeType._enumerators)

    _M_SmartHouse.CoffeeType = CoffeeType
    del CoffeeType

if '_t_CurrentlyAvailableCoffeeTypes' not in _M_SmartHouse.__dict__:
    _M_SmartHouse._t_CurrentlyAvailableCoffeeTypes = IcePy.defineSequence('::SmartHouse::CurrentlyAvailableCoffeeTypes', (), _M_SmartHouse._t_CoffeeType)

if 'Time' not in _M_SmartHouse.__dict__:
    _M_SmartHouse.Time = Ice.createTempClass()
    class Time(object):
        def __init__(self, hour=0, minute=0, second=0):
            self.hour = hour
            self.minute = minute
            self.second = second

        def __hash__(self):
            _h = 0
            _h = 5 * _h + Ice.getHash(self.hour)
            _h = 5 * _h + Ice.getHash(self.minute)
            _h = 5 * _h + Ice.getHash(self.second)
            return _h % 0x7fffffff

        def __compare(self, other):
            if other is None:
                return 1
            elif not isinstance(other, _M_SmartHouse.Time):
                return NotImplemented
            else:
                if self.hour is None or other.hour is None:
                    if self.hour != other.hour:
                        return (-1 if self.hour is None else 1)
                else:
                    if self.hour < other.hour:
                        return -1
                    elif self.hour > other.hour:
                        return 1
                if self.minute is None or other.minute is None:
                    if self.minute != other.minute:
                        return (-1 if self.minute is None else 1)
                else:
                    if self.minute < other.minute:
                        return -1
                    elif self.minute > other.minute:
                        return 1
                if self.second is None or other.second is None:
                    if self.second != other.second:
                        return (-1 if self.second is None else 1)
                else:
                    if self.second < other.second:
                        return -1
                    elif self.second > other.second:
                        return 1
                return 0

        def __lt__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r < 0

        def __le__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r <= 0

        def __gt__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r > 0

        def __ge__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r >= 0

        def __eq__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r == 0

        def __ne__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r != 0

        def __str__(self):
            return IcePy.stringify(self, _M_SmartHouse._t_Time)

        __repr__ = __str__

    _M_SmartHouse._t_Time = IcePy.defineStruct('::SmartHouse::Time', Time, (), (
        ('hour', (), IcePy._t_short),
        ('minute', (), IcePy._t_short),
        ('second', (), IcePy._t_short)
    ))

    _M_SmartHouse.Time = Time
    del Time

if 'DeviceError' not in _M_SmartHouse.__dict__:
    _M_SmartHouse.DeviceError = Ice.createTempClass()
    class DeviceError(Ice.UserException):
        def __init__(self, timeOfError=Ice._struct_marker, reason=''):
            if timeOfError is Ice._struct_marker:
                self.timeOfError = _M_SmartHouse.Time()
            else:
                self.timeOfError = timeOfError
            self.reason = reason

        def __str__(self):
            return IcePy.stringifyException(self)

        __repr__ = __str__

        _ice_id = '::SmartHouse::DeviceError'

    _M_SmartHouse._t_DeviceError = IcePy.defineException('::SmartHouse::DeviceError', DeviceError, (), False, None, (
        ('timeOfError', (), _M_SmartHouse._t_Time, False, 0),
        ('reason', (), IcePy._t_string, False, 0)
    ))
    DeviceError._ice_type = _M_SmartHouse._t_DeviceError

    _M_SmartHouse.DeviceError = DeviceError
    del DeviceError

if 'OutOfRangeExc' not in _M_SmartHouse.__dict__:
    _M_SmartHouse.OutOfRangeExc = Ice.createTempClass()
    class OutOfRangeExc(_M_SmartHouse.DeviceError):
        def __init__(self, timeOfError=Ice._struct_marker, reason=''):
            _M_SmartHouse.DeviceError.__init__(self, timeOfError, reason)

        def __str__(self):
            return IcePy.stringifyException(self)

        __repr__ = __str__

        _ice_id = '::SmartHouse::OutOfRangeExc'

    _M_SmartHouse._t_OutOfRangeExc = IcePy.defineException('::SmartHouse::OutOfRangeExc', OutOfRangeExc, (), False, _M_SmartHouse._t_DeviceError, ())
    OutOfRangeExc._ice_type = _M_SmartHouse._t_OutOfRangeExc

    _M_SmartHouse.OutOfRangeExc = OutOfRangeExc
    del OutOfRangeExc

if 'AlreadyTurnedOffExc' not in _M_SmartHouse.__dict__:
    _M_SmartHouse.AlreadyTurnedOffExc = Ice.createTempClass()
    class AlreadyTurnedOffExc(_M_SmartHouse.DeviceError):
        def __init__(self, timeOfError=Ice._struct_marker, reason=''):
            _M_SmartHouse.DeviceError.__init__(self, timeOfError, reason)

        def __str__(self):
            return IcePy.stringifyException(self)

        __repr__ = __str__

        _ice_id = '::SmartHouse::AlreadyTurnedOffExc'

    _M_SmartHouse._t_AlreadyTurnedOffExc = IcePy.defineException('::SmartHouse::AlreadyTurnedOffExc', AlreadyTurnedOffExc, (), False, _M_SmartHouse._t_DeviceError, ())
    AlreadyTurnedOffExc._ice_type = _M_SmartHouse._t_AlreadyTurnedOffExc

    _M_SmartHouse.AlreadyTurnedOffExc = AlreadyTurnedOffExc
    del AlreadyTurnedOffExc

if 'AlreadyTurnedOnExc' not in _M_SmartHouse.__dict__:
    _M_SmartHouse.AlreadyTurnedOnExc = Ice.createTempClass()
    class AlreadyTurnedOnExc(_M_SmartHouse.DeviceError):
        def __init__(self, timeOfError=Ice._struct_marker, reason=''):
            _M_SmartHouse.DeviceError.__init__(self, timeOfError, reason)

        def __str__(self):
            return IcePy.stringifyException(self)

        __repr__ = __str__

        _ice_id = '::SmartHouse::AlreadyTurnedOnExc'

    _M_SmartHouse._t_AlreadyTurnedOnExc = IcePy.defineException('::SmartHouse::AlreadyTurnedOnExc', AlreadyTurnedOnExc, (), False, _M_SmartHouse._t_DeviceError, ())
    AlreadyTurnedOnExc._ice_type = _M_SmartHouse._t_AlreadyTurnedOnExc

    _M_SmartHouse.AlreadyTurnedOnExc = AlreadyTurnedOnExc
    del AlreadyTurnedOnExc

if 'IsOffExc' not in _M_SmartHouse.__dict__:
    _M_SmartHouse.IsOffExc = Ice.createTempClass()
    class IsOffExc(_M_SmartHouse.DeviceError):
        def __init__(self, timeOfError=Ice._struct_marker, reason=''):
            _M_SmartHouse.DeviceError.__init__(self, timeOfError, reason)

        def __str__(self):
            return IcePy.stringifyException(self)

        __repr__ = __str__

        _ice_id = '::SmartHouse::IsOffExc'

    _M_SmartHouse._t_IsOffExc = IcePy.defineException('::SmartHouse::IsOffExc', IsOffExc, (), False, _M_SmartHouse._t_DeviceError, ())
    IsOffExc._ice_type = _M_SmartHouse._t_IsOffExc

    _M_SmartHouse.IsOffExc = IsOffExc
    del IsOffExc

if 'LackOfIngredientExc' not in _M_SmartHouse.__dict__:
    _M_SmartHouse.LackOfIngredientExc = Ice.createTempClass()
    class LackOfIngredientExc(_M_SmartHouse.DeviceError):
        def __init__(self, timeOfError=Ice._struct_marker, reason=''):
            _M_SmartHouse.DeviceError.__init__(self, timeOfError, reason)

        def __str__(self):
            return IcePy.stringifyException(self)

        __repr__ = __str__

        _ice_id = '::SmartHouse::LackOfIngredientExc'

    _M_SmartHouse._t_LackOfIngredientExc = IcePy.defineException('::SmartHouse::LackOfIngredientExc', LackOfIngredientExc, (), False, _M_SmartHouse._t_DeviceError, ())
    LackOfIngredientExc._ice_type = _M_SmartHouse._t_LackOfIngredientExc

    _M_SmartHouse.LackOfIngredientExc = LackOfIngredientExc
    del LackOfIngredientExc

_M_SmartHouse._t_ISmartDevice = IcePy.defineValue('::SmartHouse::ISmartDevice', Ice.Value, -1, (), False, True, None, ())

if 'ISmartDevicePrx' not in _M_SmartHouse.__dict__:
    _M_SmartHouse.ISmartDevicePrx = Ice.createTempClass()
    class ISmartDevicePrx(Ice.ObjectPrx):

        def getPowerMode(self, context=None):
            return _M_SmartHouse.ISmartDevice._op_getPowerMode.invoke(self, ((), context))

        def getPowerModeAsync(self, context=None):
            return _M_SmartHouse.ISmartDevice._op_getPowerMode.invokeAsync(self, ((), context))

        def begin_getPowerMode(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHouse.ISmartDevice._op_getPowerMode.begin(self, ((), _response, _ex, _sent, context))

        def end_getPowerMode(self, _r):
            return _M_SmartHouse.ISmartDevice._op_getPowerMode.end(self, _r)

        def turnOn(self, context=None):
            return _M_SmartHouse.ISmartDevice._op_turnOn.invoke(self, ((), context))

        def turnOnAsync(self, context=None):
            return _M_SmartHouse.ISmartDevice._op_turnOn.invokeAsync(self, ((), context))

        def begin_turnOn(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHouse.ISmartDevice._op_turnOn.begin(self, ((), _response, _ex, _sent, context))

        def end_turnOn(self, _r):
            return _M_SmartHouse.ISmartDevice._op_turnOn.end(self, _r)

        def turnOff(self, context=None):
            return _M_SmartHouse.ISmartDevice._op_turnOff.invoke(self, ((), context))

        def turnOffAsync(self, context=None):
            return _M_SmartHouse.ISmartDevice._op_turnOff.invokeAsync(self, ((), context))

        def begin_turnOff(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHouse.ISmartDevice._op_turnOff.begin(self, ((), _response, _ex, _sent, context))

        def end_turnOff(self, _r):
            return _M_SmartHouse.ISmartDevice._op_turnOff.end(self, _r)

        @staticmethod
        def checkedCast(proxy, facetOrContext=None, context=None):
            return _M_SmartHouse.ISmartDevicePrx.ice_checkedCast(proxy, '::SmartHouse::ISmartDevice', facetOrContext, context)

        @staticmethod
        def uncheckedCast(proxy, facet=None):
            return _M_SmartHouse.ISmartDevicePrx.ice_uncheckedCast(proxy, facet)

        @staticmethod
        def ice_staticId():
            return '::SmartHouse::ISmartDevice'
    _M_SmartHouse._t_ISmartDevicePrx = IcePy.defineProxy('::SmartHouse::ISmartDevice', ISmartDevicePrx)

    _M_SmartHouse.ISmartDevicePrx = ISmartDevicePrx
    del ISmartDevicePrx

    _M_SmartHouse.ISmartDevice = Ice.createTempClass()
    class ISmartDevice(Ice.Object):

        def ice_ids(self, current=None):
            return ('::Ice::Object', '::SmartHouse::ISmartDevice')

        def ice_id(self, current=None):
            return '::SmartHouse::ISmartDevice'

        @staticmethod
        def ice_staticId():
            return '::SmartHouse::ISmartDevice'

        def getPowerMode(self, current=None):
            raise NotImplementedError("servant method 'getPowerMode' not implemented")

        def turnOn(self, current=None):
            raise NotImplementedError("servant method 'turnOn' not implemented")

        def turnOff(self, current=None):
            raise NotImplementedError("servant method 'turnOff' not implemented")

        def __str__(self):
            return IcePy.stringify(self, _M_SmartHouse._t_ISmartDeviceDisp)

        __repr__ = __str__

    _M_SmartHouse._t_ISmartDeviceDisp = IcePy.defineClass('::SmartHouse::ISmartDevice', ISmartDevice, (), None, ())
    ISmartDevice._ice_type = _M_SmartHouse._t_ISmartDeviceDisp

    ISmartDevice._op_getPowerMode = IcePy.Operation('getPowerMode', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (), (), ((), _M_SmartHouse._t_PowerMode, False, 0), ())
    ISmartDevice._op_turnOn = IcePy.Operation('turnOn', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (), (), None, (_M_SmartHouse._t_AlreadyTurnedOnExc,))
    ISmartDevice._op_turnOff = IcePy.Operation('turnOff', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (), (), None, (_M_SmartHouse._t_AlreadyTurnedOffExc,))

    _M_SmartHouse.ISmartDevice = ISmartDevice
    del ISmartDevice

_M_SmartHouse._t_AirConditioning = IcePy.defineValue('::SmartHouse::AirConditioning', Ice.Value, -1, (), False, True, None, ())

if 'AirConditioningPrx' not in _M_SmartHouse.__dict__:
    _M_SmartHouse.AirConditioningPrx = Ice.createTempClass()
    class AirConditioningPrx(_M_SmartHouse.ISmartDevicePrx):

        def getTemperature(self, context=None):
            return _M_SmartHouse.AirConditioning._op_getTemperature.invoke(self, ((), context))

        def getTemperatureAsync(self, context=None):
            return _M_SmartHouse.AirConditioning._op_getTemperature.invokeAsync(self, ((), context))

        def begin_getTemperature(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHouse.AirConditioning._op_getTemperature.begin(self, ((), _response, _ex, _sent, context))

        def end_getTemperature(self, _r):
            return _M_SmartHouse.AirConditioning._op_getTemperature.end(self, _r)

        def setTemperature(self, temperature, context=None):
            return _M_SmartHouse.AirConditioning._op_setTemperature.invoke(self, ((temperature, ), context))

        def setTemperatureAsync(self, temperature, context=None):
            return _M_SmartHouse.AirConditioning._op_setTemperature.invokeAsync(self, ((temperature, ), context))

        def begin_setTemperature(self, temperature, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHouse.AirConditioning._op_setTemperature.begin(self, ((temperature, ), _response, _ex, _sent, context))

        def end_setTemperature(self, _r):
            return _M_SmartHouse.AirConditioning._op_setTemperature.end(self, _r)

        @staticmethod
        def checkedCast(proxy, facetOrContext=None, context=None):
            return _M_SmartHouse.AirConditioningPrx.ice_checkedCast(proxy, '::SmartHouse::AirConditioning', facetOrContext, context)

        @staticmethod
        def uncheckedCast(proxy, facet=None):
            return _M_SmartHouse.AirConditioningPrx.ice_uncheckedCast(proxy, facet)

        @staticmethod
        def ice_staticId():
            return '::SmartHouse::AirConditioning'
    _M_SmartHouse._t_AirConditioningPrx = IcePy.defineProxy('::SmartHouse::AirConditioning', AirConditioningPrx)

    _M_SmartHouse.AirConditioningPrx = AirConditioningPrx
    del AirConditioningPrx

    _M_SmartHouse.AirConditioning = Ice.createTempClass()
    class AirConditioning(_M_SmartHouse.ISmartDevice):

        def ice_ids(self, current=None):
            return ('::Ice::Object', '::SmartHouse::AirConditioning', '::SmartHouse::ISmartDevice')

        def ice_id(self, current=None):
            return '::SmartHouse::AirConditioning'

        @staticmethod
        def ice_staticId():
            return '::SmartHouse::AirConditioning'

        def getTemperature(self, current=None):
            raise NotImplementedError("servant method 'getTemperature' not implemented")

        def setTemperature(self, temperature, current=None):
            raise NotImplementedError("servant method 'setTemperature' not implemented")

        def __str__(self):
            return IcePy.stringify(self, _M_SmartHouse._t_AirConditioningDisp)

        __repr__ = __str__

    _M_SmartHouse._t_AirConditioningDisp = IcePy.defineClass('::SmartHouse::AirConditioning', AirConditioning, (), None, (_M_SmartHouse._t_ISmartDeviceDisp,))
    AirConditioning._ice_type = _M_SmartHouse._t_AirConditioningDisp

    AirConditioning._op_getTemperature = IcePy.Operation('getTemperature', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (), (), ((), IcePy._t_int, False, 0), (_M_SmartHouse._t_IsOffExc,))
    AirConditioning._op_setTemperature = IcePy.Operation('setTemperature', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), IcePy._t_float, False, 0),), (), None, (_M_SmartHouse._t_OutOfRangeExc, _M_SmartHouse._t_IsOffExc))

    _M_SmartHouse.AirConditioning = AirConditioning
    del AirConditioning

_M_SmartHouse._t_Alarm = IcePy.defineValue('::SmartHouse::Alarm', Ice.Value, -1, (), False, True, None, ())

if 'AlarmPrx' not in _M_SmartHouse.__dict__:
    _M_SmartHouse.AlarmPrx = Ice.createTempClass()
    class AlarmPrx(_M_SmartHouse.ISmartDevicePrx):

        def getStartTime(self, context=None):
            return _M_SmartHouse.Alarm._op_getStartTime.invoke(self, ((), context))

        def getStartTimeAsync(self, context=None):
            return _M_SmartHouse.Alarm._op_getStartTime.invokeAsync(self, ((), context))

        def begin_getStartTime(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHouse.Alarm._op_getStartTime.begin(self, ((), _response, _ex, _sent, context))

        def end_getStartTime(self, _r):
            return _M_SmartHouse.Alarm._op_getStartTime.end(self, _r)

        def setStartTime(self, startTime, context=None):
            return _M_SmartHouse.Alarm._op_setStartTime.invoke(self, ((startTime, ), context))

        def setStartTimeAsync(self, startTime, context=None):
            return _M_SmartHouse.Alarm._op_setStartTime.invokeAsync(self, ((startTime, ), context))

        def begin_setStartTime(self, startTime, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHouse.Alarm._op_setStartTime.begin(self, ((startTime, ), _response, _ex, _sent, context))

        def end_setStartTime(self, _r):
            return _M_SmartHouse.Alarm._op_setStartTime.end(self, _r)

        def getEndTime(self, context=None):
            return _M_SmartHouse.Alarm._op_getEndTime.invoke(self, ((), context))

        def getEndTimeAsync(self, context=None):
            return _M_SmartHouse.Alarm._op_getEndTime.invokeAsync(self, ((), context))

        def begin_getEndTime(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHouse.Alarm._op_getEndTime.begin(self, ((), _response, _ex, _sent, context))

        def end_getEndTime(self, _r):
            return _M_SmartHouse.Alarm._op_getEndTime.end(self, _r)

        def setEndTime(self, endTime, context=None):
            return _M_SmartHouse.Alarm._op_setEndTime.invoke(self, ((endTime, ), context))

        def setEndTimeAsync(self, endTime, context=None):
            return _M_SmartHouse.Alarm._op_setEndTime.invokeAsync(self, ((endTime, ), context))

        def begin_setEndTime(self, endTime, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHouse.Alarm._op_setEndTime.begin(self, ((endTime, ), _response, _ex, _sent, context))

        def end_setEndTime(self, _r):
            return _M_SmartHouse.Alarm._op_setEndTime.end(self, _r)

        @staticmethod
        def checkedCast(proxy, facetOrContext=None, context=None):
            return _M_SmartHouse.AlarmPrx.ice_checkedCast(proxy, '::SmartHouse::Alarm', facetOrContext, context)

        @staticmethod
        def uncheckedCast(proxy, facet=None):
            return _M_SmartHouse.AlarmPrx.ice_uncheckedCast(proxy, facet)

        @staticmethod
        def ice_staticId():
            return '::SmartHouse::Alarm'
    _M_SmartHouse._t_AlarmPrx = IcePy.defineProxy('::SmartHouse::Alarm', AlarmPrx)

    _M_SmartHouse.AlarmPrx = AlarmPrx
    del AlarmPrx

    _M_SmartHouse.Alarm = Ice.createTempClass()
    class Alarm(_M_SmartHouse.ISmartDevice):

        def ice_ids(self, current=None):
            return ('::Ice::Object', '::SmartHouse::Alarm', '::SmartHouse::ISmartDevice')

        def ice_id(self, current=None):
            return '::SmartHouse::Alarm'

        @staticmethod
        def ice_staticId():
            return '::SmartHouse::Alarm'

        def getStartTime(self, current=None):
            raise NotImplementedError("servant method 'getStartTime' not implemented")

        def setStartTime(self, startTime, current=None):
            raise NotImplementedError("servant method 'setStartTime' not implemented")

        def getEndTime(self, current=None):
            raise NotImplementedError("servant method 'getEndTime' not implemented")

        def setEndTime(self, endTime, current=None):
            raise NotImplementedError("servant method 'setEndTime' not implemented")

        def __str__(self):
            return IcePy.stringify(self, _M_SmartHouse._t_AlarmDisp)

        __repr__ = __str__

    _M_SmartHouse._t_AlarmDisp = IcePy.defineClass('::SmartHouse::Alarm', Alarm, (), None, (_M_SmartHouse._t_ISmartDeviceDisp,))
    Alarm._ice_type = _M_SmartHouse._t_AlarmDisp

    Alarm._op_getStartTime = IcePy.Operation('getStartTime', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (), (), ((), _M_SmartHouse._t_Time, False, 0), (_M_SmartHouse._t_IsOffExc,))
    Alarm._op_setStartTime = IcePy.Operation('setStartTime', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), _M_SmartHouse._t_Time, False, 0),), (), None, (_M_SmartHouse._t_OutOfRangeExc, _M_SmartHouse._t_IsOffExc))
    Alarm._op_getEndTime = IcePy.Operation('getEndTime', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (), (), ((), _M_SmartHouse._t_Time, False, 0), (_M_SmartHouse._t_IsOffExc,))
    Alarm._op_setEndTime = IcePy.Operation('setEndTime', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), _M_SmartHouse._t_Time, False, 0),), (), None, (_M_SmartHouse._t_OutOfRangeExc, _M_SmartHouse._t_IsOffExc))

    _M_SmartHouse.Alarm = Alarm
    del Alarm

_M_SmartHouse._t_Lamp = IcePy.defineValue('::SmartHouse::Lamp', Ice.Value, -1, (), False, True, None, ())

if 'LampPrx' not in _M_SmartHouse.__dict__:
    _M_SmartHouse.LampPrx = Ice.createTempClass()
    class LampPrx(_M_SmartHouse.ISmartDevicePrx):

        def getLightMode(self, context=None):
            return _M_SmartHouse.Lamp._op_getLightMode.invoke(self, ((), context))

        def getLightModeAsync(self, context=None):
            return _M_SmartHouse.Lamp._op_getLightMode.invokeAsync(self, ((), context))

        def begin_getLightMode(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHouse.Lamp._op_getLightMode.begin(self, ((), _response, _ex, _sent, context))

        def end_getLightMode(self, _r):
            return _M_SmartHouse.Lamp._op_getLightMode.end(self, _r)

        def setLightMode(self, mode, context=None):
            return _M_SmartHouse.Lamp._op_setLightMode.invoke(self, ((mode, ), context))

        def setLightModeAsync(self, mode, context=None):
            return _M_SmartHouse.Lamp._op_setLightMode.invokeAsync(self, ((mode, ), context))

        def begin_setLightMode(self, mode, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHouse.Lamp._op_setLightMode.begin(self, ((mode, ), _response, _ex, _sent, context))

        def end_setLightMode(self, _r):
            return _M_SmartHouse.Lamp._op_setLightMode.end(self, _r)

        @staticmethod
        def checkedCast(proxy, facetOrContext=None, context=None):
            return _M_SmartHouse.LampPrx.ice_checkedCast(proxy, '::SmartHouse::Lamp', facetOrContext, context)

        @staticmethod
        def uncheckedCast(proxy, facet=None):
            return _M_SmartHouse.LampPrx.ice_uncheckedCast(proxy, facet)

        @staticmethod
        def ice_staticId():
            return '::SmartHouse::Lamp'
    _M_SmartHouse._t_LampPrx = IcePy.defineProxy('::SmartHouse::Lamp', LampPrx)

    _M_SmartHouse.LampPrx = LampPrx
    del LampPrx

    _M_SmartHouse.Lamp = Ice.createTempClass()
    class Lamp(_M_SmartHouse.ISmartDevice):

        def ice_ids(self, current=None):
            return ('::Ice::Object', '::SmartHouse::ISmartDevice', '::SmartHouse::Lamp')

        def ice_id(self, current=None):
            return '::SmartHouse::Lamp'

        @staticmethod
        def ice_staticId():
            return '::SmartHouse::Lamp'

        def getLightMode(self, current=None):
            raise NotImplementedError("servant method 'getLightMode' not implemented")

        def setLightMode(self, mode, current=None):
            raise NotImplementedError("servant method 'setLightMode' not implemented")

        def __str__(self):
            return IcePy.stringify(self, _M_SmartHouse._t_LampDisp)

        __repr__ = __str__

    _M_SmartHouse._t_LampDisp = IcePy.defineClass('::SmartHouse::Lamp', Lamp, (), None, (_M_SmartHouse._t_ISmartDeviceDisp,))
    Lamp._ice_type = _M_SmartHouse._t_LampDisp

    Lamp._op_getLightMode = IcePy.Operation('getLightMode', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (), (), ((), _M_SmartHouse._t_LightMode, False, 0), (_M_SmartHouse._t_IsOffExc,))
    Lamp._op_setLightMode = IcePy.Operation('setLightMode', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), _M_SmartHouse._t_LightMode, False, 0),), (), None, (_M_SmartHouse._t_IsOffExc,))

    _M_SmartHouse.Lamp = Lamp
    del Lamp

_M_SmartHouse._t_CoffeeMaker = IcePy.defineValue('::SmartHouse::CoffeeMaker', Ice.Value, -1, (), False, True, None, ())

if 'CoffeeMakerPrx' not in _M_SmartHouse.__dict__:
    _M_SmartHouse.CoffeeMakerPrx = Ice.createTempClass()
    class CoffeeMakerPrx(_M_SmartHouse.ISmartDevicePrx):

        def makeCoffee(self, coffeeType, context=None):
            return _M_SmartHouse.CoffeeMaker._op_makeCoffee.invoke(self, ((coffeeType, ), context))

        def makeCoffeeAsync(self, coffeeType, context=None):
            return _M_SmartHouse.CoffeeMaker._op_makeCoffee.invokeAsync(self, ((coffeeType, ), context))

        def begin_makeCoffee(self, coffeeType, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHouse.CoffeeMaker._op_makeCoffee.begin(self, ((coffeeType, ), _response, _ex, _sent, context))

        def end_makeCoffee(self, _r):
            return _M_SmartHouse.CoffeeMaker._op_makeCoffee.end(self, _r)

        def addSugar(self, spoons, context=None):
            return _M_SmartHouse.CoffeeMaker._op_addSugar.invoke(self, ((spoons, ), context))

        def addSugarAsync(self, spoons, context=None):
            return _M_SmartHouse.CoffeeMaker._op_addSugar.invokeAsync(self, ((spoons, ), context))

        def begin_addSugar(self, spoons, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHouse.CoffeeMaker._op_addSugar.begin(self, ((spoons, ), _response, _ex, _sent, context))

        def end_addSugar(self, _r):
            return _M_SmartHouse.CoffeeMaker._op_addSugar.end(self, _r)

        def getAvailableCoffeeTypes(self, context=None):
            return _M_SmartHouse.CoffeeMaker._op_getAvailableCoffeeTypes.invoke(self, ((), context))

        def getAvailableCoffeeTypesAsync(self, context=None):
            return _M_SmartHouse.CoffeeMaker._op_getAvailableCoffeeTypes.invokeAsync(self, ((), context))

        def begin_getAvailableCoffeeTypes(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_SmartHouse.CoffeeMaker._op_getAvailableCoffeeTypes.begin(self, ((), _response, _ex, _sent, context))

        def end_getAvailableCoffeeTypes(self, _r):
            return _M_SmartHouse.CoffeeMaker._op_getAvailableCoffeeTypes.end(self, _r)

        @staticmethod
        def checkedCast(proxy, facetOrContext=None, context=None):
            return _M_SmartHouse.CoffeeMakerPrx.ice_checkedCast(proxy, '::SmartHouse::CoffeeMaker', facetOrContext, context)

        @staticmethod
        def uncheckedCast(proxy, facet=None):
            return _M_SmartHouse.CoffeeMakerPrx.ice_uncheckedCast(proxy, facet)

        @staticmethod
        def ice_staticId():
            return '::SmartHouse::CoffeeMaker'
    _M_SmartHouse._t_CoffeeMakerPrx = IcePy.defineProxy('::SmartHouse::CoffeeMaker', CoffeeMakerPrx)

    _M_SmartHouse.CoffeeMakerPrx = CoffeeMakerPrx
    del CoffeeMakerPrx

    _M_SmartHouse.CoffeeMaker = Ice.createTempClass()
    class CoffeeMaker(_M_SmartHouse.ISmartDevice):

        def ice_ids(self, current=None):
            return ('::Ice::Object', '::SmartHouse::CoffeeMaker', '::SmartHouse::ISmartDevice')

        def ice_id(self, current=None):
            return '::SmartHouse::CoffeeMaker'

        @staticmethod
        def ice_staticId():
            return '::SmartHouse::CoffeeMaker'

        def makeCoffee(self, coffeeType, current=None):
            raise NotImplementedError("servant method 'makeCoffee' not implemented")

        def addSugar(self, spoons, current=None):
            raise NotImplementedError("servant method 'addSugar' not implemented")

        def getAvailableCoffeeTypes(self, current=None):
            raise NotImplementedError("servant method 'getAvailableCoffeeTypes' not implemented")

        def __str__(self):
            return IcePy.stringify(self, _M_SmartHouse._t_CoffeeMakerDisp)

        __repr__ = __str__

    _M_SmartHouse._t_CoffeeMakerDisp = IcePy.defineClass('::SmartHouse::CoffeeMaker', CoffeeMaker, (), None, (_M_SmartHouse._t_ISmartDeviceDisp,))
    CoffeeMaker._ice_type = _M_SmartHouse._t_CoffeeMakerDisp

    CoffeeMaker._op_makeCoffee = IcePy.Operation('makeCoffee', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), _M_SmartHouse._t_CoffeeType, False, 0),), (), None, (_M_SmartHouse._t_IsOffExc, _M_SmartHouse._t_LackOfIngredientExc))
    CoffeeMaker._op_addSugar = IcePy.Operation('addSugar', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), IcePy._t_int, False, 0),), (), None, (_M_SmartHouse._t_LackOfIngredientExc, _M_SmartHouse._t_OutOfRangeExc, _M_SmartHouse._t_IsOffExc))
    CoffeeMaker._op_getAvailableCoffeeTypes = IcePy.Operation('getAvailableCoffeeTypes', Ice.OperationMode.Idempotent, Ice.OperationMode.Idempotent, False, None, (), (), (), ((), _M_SmartHouse._t_CurrentlyAvailableCoffeeTypes, False, 0), (_M_SmartHouse._t_IsOffExc,))

    _M_SmartHouse.CoffeeMaker = CoffeeMaker
    del CoffeeMaker

# End of module SmartHouse
