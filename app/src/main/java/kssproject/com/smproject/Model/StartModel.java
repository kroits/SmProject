package kssproject.com.smproject.Model;

import android.content.SharedPreferences;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import kssproject.com.smproject.FireBase.StoreDb;
import kssproject.com.smproject.MidStore.CalorieData;
import kssproject.com.smproject.utils.DateUtil;
import kssproject.com.smproject.utils.Key;
import kssproject.com.smproject.utils.StoreData;

/**
 * Created by b3216 on 2017-07-30.
 */

public class StartModel {
    private static final String TAG = "StartModel";

    public void checkAge(SharedPreferences sp) {
        Integer postYear;
        Integer currentYear;
        postYear = sp.getInt("Year", 0);
        if (postYear == 0) {
            Log.d(TAG, "checkAge: sharedpreference에 저장된 년도가 없습니다. ");
        } else {
            if (postYear == (currentYear = DateUtil.getInstance().getDate().getYear())) {
                Log.d(TAG, "checkAge: 년도가 바뀌지 않았습니다.");
            } else {
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("Year", currentYear);
                editor.putInt("Age", postYear + (currentYear - postYear));
                editor.commit();
            }
        }
    }

    public Boolean checkUserKey(SharedPreferences sp) {
        String key;
        key = sp.getString("UserKey", null);
        if (key == null) {
            return false;
        }
        return true;
    }

    public void loadData(SharedPreferences sp) {
        String key;
        key = sp.getString("UserKey", null);
        if (key == null) {

        } else {
            Key.getInstance().setKey(key);

        }

    }

    public void calorieSet() {
        int calorie;
        long calorieL;
        calorieL = StoreData.getInstance().getCalorie().get(StoreData.getInstance().getCalorie().size()-1);
        calorie = (int) calorieL;
        CalorieData.getInstance().setCalorie(calorie);
    }

    public void DataSet() {
        int count = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String storeStrDate = StoreData.getInstance().getDate().get(StoreData.getInstance().getDate().size()-1);
        Date nowDate = DateUtil.getInstance().getDate();
        try {
            Date storeDate = dateFormat.parse(storeStrDate);
            while (true) {
                if (storeDate.before(nowDate)) {
                    if(dateFormat.format(storeDate).equals(DateUtil.getInstance().getStrDate())){
                        break;
                    }else{
                        count++;
                        storeDate.setDate(storeDate.getDate()+1);
                    }
                }

            }

            StoreDb.getInstance().DataSave(Key.getInstance().getKey(),0f,0,count);
            StoreData.getInstance().storeNoneData(count);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
