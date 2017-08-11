package kssproject.com.smproject.MidStore;

/**
 * Created by b3216 on 2017-08-10.
 */

public class CalorieData {
    private static CalorieData mCalorieData = new CalorieData();
    private static int calorie;
    public static CalorieData getInstance() {return mCalorieData;}

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        CalorieData.calorie = calorie;
    }
}
