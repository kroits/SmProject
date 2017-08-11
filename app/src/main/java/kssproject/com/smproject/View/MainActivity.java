package kssproject.com.smproject.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import kssproject.com.smproject.MidStore.CalorieData;
import kssproject.com.smproject.Presenter.Main.MainPresenter;
import kssproject.com.smproject.Presenter.Main.MainPresenterImpl;
import kssproject.com.smproject.R;
import kssproject.com.smproject.utils.DateUtil;
import kssproject.com.smproject.utils.Key;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainPresenter.View{

    private MainPresenter mainPresenter;

    private String TAG = "kss";
    private Button button;
    private Button button2;
    private Button modify;
    private TextView name;
    private SharedPreferences sp;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();


    //// TODO: 2017-07-25  graph

    private LineChartView chart;
    private String key = Key.getInstance().getKey();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mainPresenter = new MainPresenterImpl(MainActivity.this);
        mainPresenter.setView(MainActivity.this);

        sp =  getSharedPreferences("profile",MODE_PRIVATE);


        name = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_name);
        button = (Button) findViewById(R.id.button2);
        button2 = (Button)findViewById(R.id.buttonBurn);
        modify = (Button) navigationView.getHeaderView(0).findViewById(R.id.bn_modify);
        chart = (LineChartView) findViewById(R.id.chart_top);


        name.setText(sp.getString("Name",""));
        sp.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                name.setText(sharedPreferences.getString(key,""));
            }
        });


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
                // 운동모드 진입 시 날짜의 변화를 체크.
                if(DateUtil.getInstance().checkDate()){

                }else{
                    //날짜가 변경되었을경우 날짜를 새로 저장, calorie저장값을 0으로 변경
                    DateUtil.getInstance().reSettingDate();
                    CalorieData.getInstance().setCalorie(0);
                }
                mainPresenter.clickedExercise();
            }
        });

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mainPresenter.
            }
        });

    }

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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_recent) {
            mainPresenter.clickedRecentData();
        } else if (id == R.id.nav_post) {
            mainPresenter.clickedAllData();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }
}
