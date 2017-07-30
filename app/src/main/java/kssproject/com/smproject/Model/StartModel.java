package kssproject.com.smproject.Model;

import android.content.SharedPreferences;

import kssproject.com.smproject.utils.DateToday;
import kssproject.com.smproject.utils.Key;

/**
 * Created by b3216 on 2017-07-30.
 */

public class StartModel {

    public void checkAge(SharedPreferences sp){
        Integer postYear;
        Integer currentYear;
        postYear = sp.getInt("Year",0);
        if(postYear == 0){

        }
        else{
            if(postYear==(currentYear = DateToday.getInstance().getDate().getYear())){

            }
            else{
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("Year",currentYear);
                editor.putInt("Age",postYear + (currentYear-postYear));
                editor.commit();
            }
        }
    }

    public Boolean checkUserKey(SharedPreferences sp){
        String key;
        key = sp.getString("UserKey",null);
        if(key == null){
            return false;
        }
        return true;
    }

    public void loadData(SharedPreferences sp) {
        String key;
        key = sp.getString("UserKey",null);
        if(key == null){

        }
        else{
            Key.getInstance().setKey(key);

        }

    }
}
