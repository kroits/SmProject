package kssproject.com.smproject.FireBase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Iterator;

import kssproject.com.smproject.utils.CustomClass;
import kssproject.com.smproject.utils.StoreData;

/**
 * Created by b3216 on 2017-07-17.
 */

public class SelectDb {
    private static SelectDb mSelectDb = new SelectDb();
    private static FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private static DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference userRef = null;


    private SelectDb(){}

    public static SelectDb getInstance(){return mSelectDb;}

    public void SelectData(DataSnapshot dataSnapshot){

                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                StoreData sd = StoreData.getInstance();
                Long calorie;
                Double weight;
                String date;
                CustomClass mCustomClass = new CustomClass();
                sd.dataClear();
                while(items.hasNext()){
                    DataSnapshot item = items.next();

                    calorie = (Long)item.child("calorie").getValue();
                    sd.getCalorie().add(calorie);
                    mCustomClass.setValue(item.child("weight").getValue());
                    weight = mCustomClass.getValue();
                    sd.getWeight().add(weight);
                    date = (String)item.getKey();
                    sd.getDate().add(date);

                }
            }


}
