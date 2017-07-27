package kssproject.com.smproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import kssproject.com.smproject.Controller.BusProvider;
import kssproject.com.smproject.Controller.WaitStart;
import kssproject.com.smproject.FireBase.SelectDb;

public class WaitActivity extends AppCompatActivity {
    public static Bus bus;

    public Intent postIntent ;
    private String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);



        this.postIntent = getIntent();
        this.key = postIntent.getStringExtra("UserKey");
        BusProvider.getInstance().register(this);
        loadData();
//        onDestroy();
    }

    private void loadData() {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(key).child("information").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                WaitStart o = new WaitStart(postIntent);
                BusProvider.getInstance().post(o);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected  void onDestroy(){
        BusProvider.getInstance().unregister(this);
        super.onDestroy();
    }
    @Subscribe
    public void nextMethod(WaitStart o) {
        Intent intent = o.getIntent();
        SelectDb.getInstance().SelectData(key);
        Intent maintent = new Intent(WaitActivity.this,MainActivity.class);
        startActivity(maintent);
        finish();
    }

}
