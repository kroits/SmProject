package kssproject.com.smproject.FireBase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by b3216 on 2017-07-28.
 */

public class ModifyDb {
    private static ModifyDb mModifyDb = new ModifyDb();
    private static FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private static DatabaseReference databaseReference = firebaseDatabase.getReference();


    private ModifyDb(){}

    public static ModifyDb getInstance(){return mModifyDb;}

    public void WeightModify(String key, String strDate ,float weight){
        Map<String, Object> WeightUpdates = new HashMap<String, Object>();
        WeightUpdates.put("weight",weight);
        databaseReference.child(key).child("information").child(strDate).updateChildren(WeightUpdates);
    }

    public void CalorieModify(String key, String strDate, int calorie){
        Map<String, Object> CalorieUpdates = new HashMap<String, Object>();
        CalorieUpdates.put("calorie",calorie);
        databaseReference.child(key).child("information").child(strDate).updateChildren(CalorieUpdates);
    }
}
