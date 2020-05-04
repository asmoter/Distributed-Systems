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

public class LackOfIngredientExc extends DeviceError
{
    public LackOfIngredientExc()
    {
        super();
    }

    public LackOfIngredientExc(Throwable cause)
    {
        super(cause);
    }

    public LackOfIngredientExc(Time timeOfError, String reason)
    {
        super(timeOfError, reason);
    }

    public LackOfIngredientExc(Time timeOfError, String reason, Throwable cause)
    {
        super(timeOfError, reason, cause);
    }

    public String ice_id()
    {
        return "::SmartHouse::LackOfIngredientExc";
    }

    /** @hidden */
    @Override
    protected void _writeImpl(com.zeroc.Ice.OutputStream ostr_)
    {
        ostr_.startSlice("::SmartHouse::LackOfIngredientExc", -1, false);
        ostr_.endSlice();
        super._writeImpl(ostr_);
    }

    /** @hidden */
    @Override
    protected void _readImpl(com.zeroc.Ice.InputStream istr_)
    {
        istr_.startSlice();
        istr_.endSlice();
        super._readImpl(istr_);
    }

    /** @hidden */
    public static final long serialVersionUID = 8082379801205625293L;
}
