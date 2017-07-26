package kssproject.com.smproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kssproject.com.smproject.Exercise.ExersiceActivity;
import kssproject.com.smproject.FireBase.SelectDb;
import kssproject.com.smproject.MidStore.StoreData;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

public class MainActivity extends AppCompatActivity {


    private Button button;
    private Button button2;
    private Button buttonburn;
    private String TAG = "kss";
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference userRef = null;


    //// TODO: 2017-07-25  graph

    private Date date = new Date();
    private LineChartView chartTop;
    private LineChartData lineData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        SharedPreferences.removeSettingAll(getApplicationContext(),"Name");
        initView();
        generateInitialLineData();

        java.util.Locale.getDefault();
//        StoreDb.getInstatce().DataSave(SharedPreferences.getSettingItem(getApplicationContext(),"UserKey"),54.4,0);

    }

    void initView() {
        button = (Button) findViewById(R.id.button2);
        chartTop = (LineChartView) findViewById(R.id.chart_top);

        buttonburn = (Button) findViewById(R.id.buttonBurn);

        final String key;
        if (SharedPreferences.getSettingItem(getApplicationContext(), "UserKey") == null) {
            SharedPreferences.saveSettingItem(getApplicationContext(), "UserKey", databaseReference.push().getKey());
            key = SharedPreferences.getSettingItem(getApplicationContext(), "UserKey");
        } else {
            key = SharedPreferences.getSettingItem(getApplicationContext(), "UserKey");
        }


        databaseReference.child(key).child("information").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                SelectDb.getInstance().SelectData(key);
                generateInitialLineData();
            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                SelectDb.getInstance().SelectData(key);
                generateInitialLineData();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                SelectDb.getInstance().SelectData(key);
                generateInitialLineData();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        buttonburn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentburn = new Intent(MainActivity.this, ExersiceActivity.class);
                startActivity(intentburn);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentdata = new Intent(MainActivity.this, DataActivity.class);
                startActivity(intentdata);
            }
        });


    }

    //// TODO: 2017-07-25  graph
    private void generateInitialLineData() {
        StoreData storeData = StoreData.getInstance();
        int numValues = 0;
        if (storeData.getDate().size() > 7) {
            numValues = storeData.getDate().size() - 7;
        } else {
            numValues = storeData.getDate().size();
        }

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<PointValue> values = new ArrayList<PointValue>();
        for (int i = 0, j = numValues; j < storeData.getDate().size(); ++i, j++) {
            values.add(new PointValue(i, storeData.getCalorie().get(j)));
            axisValues.add(new AxisValue(i).setLabel(storeData.getDate().get(j).substring(5)));
        }

//        Axis calAxis = new Axis(axisValues).setName("Calories [kcal]").setHasLines(true).setMaxLabelChars(5);
//        for(int i = 0; i < 7; ++i){
//            values.add(new PointValue(i,0));
//
//        }

        Line line = new Line(values);
        line.setColor(ChartUtils.COLOR_ORANGE).setCubic(true);


        line.setHasLabels(true);  // 그래프 y값 표시

        List<Line> lines = new ArrayList<Line>();
        line.setStrokeWidth(3);
        lines.add(line);

        lineData = new LineChartData(lines);
        lineData.setAxisXBottom(new Axis(axisValues).setHasLines(true));
        lineData.setAxisYLeft(new Axis().setName("Cal [kcal]").setHasLines(true).setMaxLabelChars(5));

        chartTop.setLineChartData(lineData);

        Viewport testv = chartTop.getMaximumViewport();
        testv.set(testv.left, testv.top, testv.right + 1, 0);
        Viewport v = new Viewport(0, 3000, 7, 0);

        testv.top = testv.top+400;
        testv.bottom = testv.bottom-190;
//        v.bottom=0;
//        v.top=3000;
//        v.left=0;
//        v.right=7;
        chartTop.setMaximumViewport(testv);
        chartTop.setCurrentViewport(testv);
//        chartTop.setScrollEnabled(false);
//        chartTop.setZoomEnabled(false);
//        chartTop.refreshDrawableState();
    }
}
