package kssproject.com.smproject.FireBase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import kssproject.com.smproject.DataPackage.FirebaseDTO;
import kssproject.com.smproject.utils.DateUtil;

/**
 * Created by b3216 on 2017-07-13.
 */

public class StoreDb {
    private static StoreDb mStoreDb = new StoreDb();
    private static FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private static DatabaseReference databaseReference = firebaseDatabase.getReference();
    private  String strDate;

    private StoreDb(){}

    public static StoreDb getInstance(){
        return mStoreDb;
    }


    public  void DataSave(String key , double weight, int calorie, int counter ) {


        Date date = DateUtil.getInstance().getDate();
        Date date2 = new Date();
        date2.setDate(date.getDate()+1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i <counter; i++) {
            date2.setDate(date2.getDate() - 1);
            strDate = dateFormat.format(date2);
            FirebaseDTO firebaseDTO = new FirebaseDTO((Math.random()*50),(int)(Math.random()*2000));

            databaseReference.child(key).child("information").child(strDate.toString()).setValue(firebaseDTO);
        }
    }

}