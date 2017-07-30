package kssproject.com.smproject.utils;

/**
 * Created by b3216 on 2017-07-21.
 */

public class CustomClass {
    private Double value;

    public CustomClass(){}

    public Double getValue(){
        return value;
    }

    public void setValue(Object value) {
        if (value instanceof Double){
            this.value = (Double) value;
        } else if (value instanceof Long){
            this.value = Double.valueOf((Long)value);
        }
    }
}
