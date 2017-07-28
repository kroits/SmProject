package kssproject.com.smproject.FireBase;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
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
            FirebaseDTO firebaseDTO = new FirebaseDTO(date.getTime(), (double) weight, calorie);
            databaseReference.child(key).child("information").child(strDate.toString()).setValue(firebaseDTO);
            databaseReference.child(key).child("information").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                FirebaseDTO firebaseDTO = dataSnapshot.getValue(FirebaseDTO.class);
//                adapter.add(strDate + " - " + firebaseDTO.getWeight());
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
    }


    //  key값, strDate = 오늘 날짜, value = 몸무게, 칼로리 값을 전달받아 오늘부터 이전 6일까지의(총7일)
    //  value값을 firebase에서 가져옴.
    //  strDate를 Date 타입으로 변경, Date.getDate()-6을 이용하여 6일 이전 Date를 구하고
    //  Date.after()를 이용하여 날짜를 비교하며 값을 가져옴
    public void DataSelect(String key, String strDate, String value) {
        Date date;
        Date strToDate;     // 전달받은 String type 오늘 날짜를 Date type으로 저장.
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        FirebaseDTO firebaseDTO = new FirebaseDTO();
         int[] calArray = new int[7];
         String[] dateArray = new String[7];
         int i = 0;
        databaseReference.child(key).child("information").addChildEventListener(new ChildEventListener(){


            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        try {

            date = dateFormat.parse(databaseReference.child(key).child("information").getKey());
            strToDate = dateFormat.parse(strDate);
            if(strToDate.after(date)){

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void setDate(String strDate){
        this.strDate = strDate;
    }
}