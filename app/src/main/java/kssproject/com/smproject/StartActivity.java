package kssproject.com.smproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import kssproject.com.smproject.FireBase.SelectDb;
import kssproject.com.smproject.FireBase.StoreDb;

public class StartActivity extends AppCompatActivity {
    private Date date;
    private String strDate;
    private static FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private static DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference userRef = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);

        String key =  SharedPreferences.getSettingItem(getApplicationContext(),"UserKey");
        date = new Date();
        strDate = dateFormat.format(date);
        StoreDb.getInstatce().setDate(strDate);

        for(int i = 0 ; i < 3 ; i ++ ) {
            SelectDb.getInstance().SelectData(key, strDate);
        }


        if(SharedPreferences.getSettingItem(getApplicationContext(),"UserKey") != null){
            Intent mainIntent = new Intent(this,MainActivity.class);
            startActivity(mainIntent);
        }
        else{
            Intent profileIntent = new Intent(this,ProfileActivity.class);
            startActivity(profileIntent);
        }
    }
}
