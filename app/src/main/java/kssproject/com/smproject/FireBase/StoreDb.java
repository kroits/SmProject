package kssproject.com.smproject.FireBase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import kssproject.com.smproject.DataPackage.FirebaseDTO;

/**
 * Created by b3216 on 2017-07-13.
 */

public class StoreDb {
    private static StoreDb mStoreDb = new StoreDb();
    private static FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private static DatabaseReference databaseReference = firebaseDatabase.getReference();
    private static DatabaseReference userRef = null;
    private  String strDate;

    private StoreDb(){}

    public static StoreDb getInstance(){
        return mStoreDb;
    }


    public  void DataSave(String key , double weight, int calorie ) {

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 5; i++) {
            date.setDate(date.getDate() - 1);
            strDate = dateFormat.format(date);
            FirebaseDTO firebaseDTO = new FirebaseDTO((double)weight, calorie);
            databaseReference.child(key).child("information").child(strDate.toString()).setValue(firebaseDTO);
        }
    }

    public void setDate(String strDate){
        this.strDate = strDate;
    }
}