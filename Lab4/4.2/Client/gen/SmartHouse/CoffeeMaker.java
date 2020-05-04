//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.3
//
// <auto-generated>
//
// Generated from file `smartHouse.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package SmartHouse;

public interface CoffeeMaker extends ISmartDevice
{
    void makeCoffee(CoffeeType coffeeType, com.zeroc.Ice.Current current)
        throws IsOffExc,
               LackOfIngredientExc;

    void addSugar(int spoons, com.zeroc.Ice.Current current)
        throws IsOffExc,
               LackOfIngredientExc,
               OutOfRangeExc;

    CoffeeType[] getAvailableCoffeeTypes(com.zeroc.Ice.Current current)
        throws IsOffExc;

    /** @hidden */
    static final String[] _iceIds =
    {
        "::Ice::Object",
        "::SmartHouse::CoffeeMaker",
        "::SmartHouse::ISmartDevice"
    };

    @Override
    default String[] ice_ids(com.zeroc.Ice.Current current)
    {
        return _iceIds;
    }

    @Override
    default String ice_id(com.zeroc.Ice.Current current)
    {
        return ice_staticId();
    }

    static String ice_staticId()
    {
        return "::SmartHouse::CoffeeMaker";
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
     * @throws com.zeroc.Ice.UserException -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_makeCoffee(CoffeeMaker obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        CoffeeType iceP_coffeeType;
        iceP_coffeeType = CoffeeType.ice_read(istr);
        inS.endReadParams();
        obj.makeCoffee(iceP_coffeeType, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
     * @throws com.zeroc.Ice.UserException -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_addSugar(CoffeeMaker obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        int iceP_spoons;
        iceP_spoons = istr.readInt();
        inS.endReadParams();
        obj.addSugar(iceP_spoons, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
     * @throws com.zeroc.Ice.UserException -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_getAvailableCoffeeTypes(CoffeeMaker obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        com.zeroc.Ice.Object._iceCheckMode(com.zeroc.Ice.OperationMode.Idempotent, current.mode);
        inS.readEmptyParams();
        CoffeeType[] ret = obj.getAvailableCoffeeTypes(current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        CurrentlyAvailableCoffeeTypesHelper.write(ostr, ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    /** @hidden */
    final static String[] _iceOps =
    {
        "addSugar",
        "getAvailableCoffeeTypes",
        "getPowerMode",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "makeCoffee",
        "turnOff",
        "turnOn"
    };

    /** @hidden */
    @Override
    default java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceDispatch(com.zeroc.IceInternal.Incoming in, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        int pos = java.util.Arrays.binarySearch(_iceOps, current.operation);
        if(pos < 0)
        {
            throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return _iceD_addSugar(this, in, current);
            }
            case 1:
            {
                return _iceD_getAvailableCoffeeTypes(this, in, current);
            }
            case 2:
            {
                return ISmartDevice._iceD_getPowerMode(this, in, current);
            }
            case 3:
            {
                return com.zeroc.Ice.Object._iceD_ice_id(this, in, current);
            }
            case 4:
            {
                return com.zeroc.Ice.Object._iceD_ice_ids(this, in, current);
            }
            case 5:
            {
                return com.zeroc.Ice.Object._iceD_ice_isA(this, in, current);
            }
            case 6:
            {
                return com.zeroc.Ice.Object._iceD_ice_ping(this, in, current);
            }
            case 7:
            {
                return _iceD_makeCoffee(this, in, current);
            }
            case 8:
            {
                return ISmartDevice._iceD_turnOff(this, in, current);
            }
            case 9:
            {
                return ISmartDevice._iceD_turnOn(this, in, current);
            }
        }

        assert(false);
        throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
    }
}
