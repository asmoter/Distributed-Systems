package restservice.rest;

import com.google.common.math.Quantiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.OptionalDouble;

public class Operations {

    public double average(ArrayList<Double> values){
        OptionalDouble avg = values.stream().mapToDouble(a -> a).average();
        if(avg.isPresent()){
            return avg.getAsDouble();
        }
        else return 0;
    }

    public double minimum(ArrayList<Double> values){
        return Collections.min(values);
    }

    public double maximum(ArrayList<Double> values){
        return Collections.max(values);
    }

    public double median(ArrayList<Double> values){
        return Quantiles.median().compute(values);
    }
}
