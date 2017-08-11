package kssproject.com.smproject.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

import kssproject.com.smproject.Presenter.Main.MainPresenter;
import kssproject.com.smproject.Presenter.Main.MainPresenterImpl;
import kssproject.com.smproject.R;
import kssproject.com.smproject.utils.DateUtil;
import kssproject.com.smproject.utils.Key;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class MainActivity_post extends AppCompatActivity implements MainPresenter.View {

    private MainPresenter mainPresenter;

    private String TAG = "kss";
    private Button button;
    private Button button2;
    private Button modify;
    private TextView name;
    private SharedPreferences sp;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();


    // TODO: 2017-08-04 compare date
    Date date = new Date();
    String strDate = DateUtil.getInstance().getStrDate();

    //// TODO: 2017-07-25  graph

    private LineChartView chart;
    private String key = Key.getInstance().getKey();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_post);

        sp =  getSharedPreferences("profile",MODE_PRIVATE);

        mainPresenter = new MainPresenterImpl(MainActivity_post.this);
        mainPresenter.setView(MainActivity_post.this);

        name = (TextView) findViewById(R.id.tv_name);
        button = (Button) findViewById(R.id.button2);
        button2 = (Button) findViewById(R.id.buttonBurn);
        modify = (Button) findViewById(R.id.bn_modify);
        chart = (LineChartView) findViewById(R.id.chart_top);

        name.setText(sp.getString("Name",""));
        sp.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                name.setText(sharedPreferences.getString(key,""));
            }
        });

        String[] values = new String[]{
                "최근 30일 기록",
                "이전 기록",
                "프로필 수정"
        };


        databaseReference.child(key).child("information").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mainPresenter.changeGraphData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.clickedAllData();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainPresenter.clickedExercise();
            }
        });

    }
    //// TODO: 2017-07-25  graph


    @Override
    public void graphView(LineChartData lineData) {
        chart.setLineChartData(lineData);
        Viewport calorieView = chart.getMaximumViewport();
        calorieView.set(calorieView.left, calorieView.top, calorieView.right + 1, 0);
        calorieView.top = calorieView.top + 400;
        chart.setMaximumViewport(calorieView);
        chart.setCurrentViewport(calorieView);
        chart.setZoomEnabled(false);
    }

    @Override
    public void otherActivity(Class activity) {
        Intent DataIntent = new Intent(this, activity);
        startActivity(DataIntent);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.menu,menu);
//        return true;
//    }


}
