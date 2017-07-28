package kssproject.com.smproject.FireBase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

import kssproject.com.smproject.DataPackage.CustomClass;
import kssproject.com.smproject.DataPackage.FirebaseDTO;
import kssproject.com.smproject.MidStore.StoreData;

/**
 * Created by b3216 on 2017-07-17.
 */

public class SelectDb {
    private static SelectDb mSelectDb = new SelectDb();
    private static FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private static DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference userRef = null;
    private String[] dateArray;
    private Integer[] calorieArray;
    private float[] weightArray;


    private SelectDb(){}

    public static SelectDb getInstance(){return mSelectDb;}

    public void SelectData(String key){
        FirebaseDTO firebaseDTO = new FirebaseDTO();

        databaseReference.child(key).child("information").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
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

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
