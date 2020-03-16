package restservice.rest;

import java.util.ArrayList;

public class Currency {

    private String base; // base currency
    private String convertTo; //
    private ArrayList<Double> values; // values of converted currency from multiple sites

    public Currency(String base, String to) {
        this.base = base;
        this.convertTo = to;
        this.values = new ArrayList<Double>();
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getConvertTo() {
        return convertTo;
    }

    public void setConvertTo(String convertTo) {
        this.convertTo = convertTo;
    }


    public ArrayList<Double> getValues() {
        return values;
    }

    public void setValues(ArrayList<Double> values) {
        this.values = values;
    }

}
