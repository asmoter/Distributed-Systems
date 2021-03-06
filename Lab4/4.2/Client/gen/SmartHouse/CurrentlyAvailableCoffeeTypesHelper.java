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

/**
 * Helper class for marshaling/unmarshaling CurrentlyAvailableCoffeeTypes.
 **/
public final class CurrentlyAvailableCoffeeTypesHelper
{
    public static void write(com.zeroc.Ice.OutputStream ostr, CoffeeType[] v)
    {
        if(v == null)
        {
            ostr.writeSize(0);
        }
        else
        {
            ostr.writeSize(v.length);
            for(int i0 = 0; i0 < v.length; i0++)
            {
                CoffeeType.ice_write(ostr, v[i0]);
            }
        }
    }

    public static CoffeeType[] read(com.zeroc.Ice.InputStream istr)
    {
        final CoffeeType[] v;
        final int len0 = istr.readAndCheckSeqSize(1);
        v = new CoffeeType[len0];
        for(int i0 = 0; i0 < len0; i0++)
        {
            v[i0] = CoffeeType.ice_read(istr);
        }
        return v;
    }

    public static void write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<CoffeeType[]> v)
    {
        if(v != null && v.isPresent())
        {
            write(ostr, tag, v.get());
        }
    }

    public static void write(com.zeroc.Ice.OutputStream ostr, int tag, CoffeeType[] v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            CurrentlyAvailableCoffeeTypesHelper.write(ostr, v);
            ostr.endSize(pos);
        }
    }

    public static java.util.Optional<CoffeeType[]> read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            CoffeeType[] v;
            v = CurrentlyAvailableCoffeeTypesHelper.read(istr);
            return java.util.Optional.of(v);
        }
        else
        {
            return java.util.Optional.empty();
        }
    }
}
