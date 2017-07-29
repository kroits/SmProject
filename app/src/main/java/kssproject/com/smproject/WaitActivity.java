package kssproject.com.smproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import kssproject.com.smproject.DataPackage.DateToday;
import kssproject.com.smproject.FireBase.SelectDb;

public class WaitActivity extends AppCompatActivity {

    private SharedPreferences sp = getSharedPreferences("profile", MODE_PRIVATE);
    private String key;
    private SharedPreferences.Editor editor = sp.edit();
    private String strDate = DateToday.getInstance().getStrDate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);

        String date = sp.getString("StrDate", null);


        //// TODO: 2017-07-29 strDate, calorie add to SharedPreference .


        if ((key = sp.getString("UserKey", null)) == null) {
            Intent intentProfile = new Intent(WaitActivity.this, ProfileActivity.class);
            startActivity(intentProfile);
            finish();
        } else {
            if (date != strDate) {
                editor.putString("StrDate", strDate);
                editor.remove("Calorie");
                editor.putInt("Calorie", 0);
                editor.commit();
            }
        }
        initAge();
        loadData();

    }

    private void initAge() {
        Integer year = DateToday.getInstance().getDate().getYear();
        Integer postYear;
        if ((postYear = sp.getInt("Year", year)) != year) {
            editor.remove("Age");
            editor.remove("Year");
            editor.putInt("Year", year);
            editor.putInt("Age", postYear + (year - postYear));
            editor.commit();
        }
    }

    private void loadData() {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(key).child("information").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                SelectDb.getInstance().SelectData(key);
                Intent intent = new Intent(WaitActivity.this, MainActivity.class);
                intent.putExtra("UserKey", key);
                startActivity(intent);
                finish();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
