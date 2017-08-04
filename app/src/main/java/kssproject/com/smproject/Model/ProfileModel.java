package kssproject.com.smproject.Model;

import android.content.SharedPreferences;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import kssproject.com.smproject.FireBase.StoreDb;
import kssproject.com.smproject.utils.DateToday;
import kssproject.com.smproject.utils.Key;

/**
 * Created by b3216 on 2017-07-30.
 */

public class ProfileModel {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();


    public void storeProfile(String name, Integer age, Float height, Float weight, Float goalweight,String sex,SharedPreferences sp){
        String key;
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("Name",name);
        editor.putInt("Age",age);
        editor.putFloat("Height",height);
        editor.putFloat("Weight",weight);
        editor.putFloat("GoalWeight",goalweight);
        editor.putString("Sex",sex);
        editor.putInt("Year", DateToday.getInstance().getDate().getYear());
        editor.putString("StrDate",DateToday.getInstance().getStrDate());
        editor.putInt("Calorie",0);
        editor.putString("UserKey",key = databaseReference.push().getKey());
        Key.getInstance().setKey(key);
        editor.commit();

        StoreDb.getInstance().DataSave(key,0f,0);

    }

}
