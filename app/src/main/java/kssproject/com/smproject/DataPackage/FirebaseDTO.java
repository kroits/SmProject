package kssproject.com.smproject.DataPackage;

/**
 * Created by b3216 on 2017-05-26.
 */

public class FirebaseDTO {
    private long date;
    private double weight;
    private int calorie;

    public FirebaseDTO(){}

    public FirebaseDTO(long date, double weight, int calorie){
        this.date = date;
        this.weight = weight;
        this.calorie = calorie;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }
}
