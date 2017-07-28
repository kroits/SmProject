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

import kssproject.com.smproject.FireBase.SelectDb;

public class WaitActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);

        sp = getSharedPreferences("profile", MODE_PRIVATE);

        if ((key = sp.getString("UserKey", null)) == null) {
            Intent intentProfile = new Intent(WaitActivity.this, ProfileActivity.class);
            startActivity(intentProfile);
            finish();
        }
            loadData();


    }

    private void loadData() {
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child(key).child("information").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    SelectDb.getInstance().SelectData(key);
                    Intent intent = new Intent(WaitActivity.this,MainActivity.class);
                    intent.putExtra("UserKey",key);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
    }

}
