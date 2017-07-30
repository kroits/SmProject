package kssproject.com.smproject.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import kssproject.com.smproject.FireBase.SelectDb;
import kssproject.com.smproject.Presenter.Start.StartPresenter;
import kssproject.com.smproject.Presenter.Start.StartPresenterImpl;
import kssproject.com.smproject.R;
import kssproject.com.smproject.utils.Key;

public class StartActivity extends AppCompatActivity implements StartPresenter.View{

    private StartPresenter startPresenter;
    private Button startButton;
    private SharedPreferences sp;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        sp = getSharedPreferences("profile", MODE_PRIVATE);
        startPresenter = new StartPresenterImpl(StartActivity.this, sp);
        startPresenter.setView(this);

        startButton = (Button)findViewById(R.id.bnStart);
        startButton.setVisibility(View.INVISIBLE);

        startPresenter.checkRegist();
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPresenter.startClicked();
            }
        });

    }

    @Override
    public void startProfile(Class nextActivity) {
        Intent changeIntent = new Intent(this,nextActivity);
        startActivity(changeIntent);
        finish();
    }

    @Override
    public void startMain(Class nextActivity) {

        Intent changeIntent = new Intent(this,nextActivity);
        startActivity(changeIntent);
        finish();
    }

    @Override
    public void loadData() {
        int i = 0;
        databaseReference.child(Key.getInstance().getKey()).child("information").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SelectDb.getInstance().SelectData(dataSnapshot);
                startPresenter.loading();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void visibleButton() {
        startButton.setVisibility(View.VISIBLE);
    }


}
