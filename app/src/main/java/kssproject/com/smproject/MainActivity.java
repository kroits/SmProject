package kssproject.com.smproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kssproject.com.smproject.Exercise.ExersiceActivity;
import kssproject.com.smproject.MidStore.StoreData;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

import static kssproject.com.smproject.SharedPreferences.removeSettingAll;

public class MainActivity extends AppCompatActivity {


    private Button button;
    private Button button2;
    private Button buttonburn;
    private String TAG = "kss";
    private ListView listView;
    private EditText editText;
    private ArrayAdapter adapter;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference userRef = null;
    private String userId = null;
//    private Date date ;
    private int calorie = 0;


    //// TODO: 2017-07-25  graph
    private Date date1 = new Date();
    private List<String> ndays = new ArrayList<String>();
    private int vmonth = date1.getMonth();
    private int vday = date1.getDate();
    private int i = 0;
    private String[] testday=new String[7];

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    private Date date = new Date();
    private String strDate = dateFormat.format(date);
    private LineChartView chartTop;
    private LineChartData lineData;

//    public void makeArray(){
//        for(i=0;i<7;i++){
//            ndays.add(i,vmonth + "-" + vday);
//            date1.setDate(date1.getDate()+1);
//            vmonth = date1.getMonth()+1;
//            vday = date1.getDate();
//        }
//        for(i=0; i< ndays.size(); i++) {
//            testday[i] = ndays.get(i);
//        }
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

//        String key =  SharedPreferences.getSettingItem(getApplicationContext(),"UserKey");
//        SelectDb.getInstance().SelectData(key, strDate);
//        date = new Date();
//        strDate = dateFormat.format(date);
//
//        StoreDb.getInstatce().setDate(strDate);

        initView();
        generateInitialLineData();





        java.util.Locale.getDefault();

        removeSettingAll(this,"height");
        removeSettingAll(this,"weight");
//        StoreDb.getInstatce().DataSave(SharedPreferences.getSettingItem(getApplicationContext(),"UserKey"),54.4,0);

    }

    void initView(){
        int cal=0;
//        listView = (ListView)findViewById(R.id.listView);
//        button2 = (Button)findViewById(R.id.button2);
        editText = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);
        chartTop = (LineChartView) findViewById(R.id.chart_top);
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,android.R.id.text1);
//        listView.setAdapter(adapter);
        buttonburn = (Button)findViewById(R.id.buttonBurn);

//        SharedPreferences.removeSettingAll(getApplicationContext(),"Name");


        if(SharedPreferences.getSettingItem(getApplicationContext(),"Name") == null){
            Intent intent_profile = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent_profile);
        }

        final String key;
        if(SharedPreferences.getSettingItem(getApplicationContext(),"UserKey") == null){
            SharedPreferences.saveSettingItem(getApplicationContext(),"UserKey",databaseReference.push().getKey());
            key = SharedPreferences.getSettingItem(getApplicationContext(),"UserKey");
        } else {
            key = SharedPreferences.getSettingItem(getApplicationContext(),"UserKey");
        }


        databaseReference.child(key).child("information").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                generateInitialLineData();
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
        // test line
//        StoreDb.getInstatce().DataSave(SharedPreferences.getSettingItem(getApplicationContext(),"UserKey"),0,0);
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseDTO firebaseDTO = new FirebaseDTO(date.getTime(), Float.parseFloat(editText.getText().toString()), calorie );
//                databaseReference.child(key).child("information").child(strDate.toString()).setValue(firebaseDTO);
////                databaseReference.child("users").child("information").push().setValue(firebaseDTO);
//            }
//        });
//        databaseReference.child(key).child("information").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                FirebaseDTO firebaseDTO = dataSnapshot.getValue(FirebaseDTO.class);
////                adapter.add(dataSnapshot.getKey() + " - " + Math.round(firebaseDTO.getWeight()*10)/10.0);
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                FirebaseDTO firebaseDTO = dataSnapshot.getValue(FirebaseDTO.class);
////                adapter.add(dataSnapshot.getKey() + " - " + Math.round(firebaseDTO.getWeight()*10)/10.0);
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });



//        button.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Intent intent=new Intent(MainActivity.this,ProfileActivity.class);
//                startActivity(intent);
//            }
//        });

        buttonburn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentburn = new Intent(MainActivity.this, ExersiceActivity.class);
                startActivity(intentburn);
            }
        });


        //// TODO: 2017-07-25  graph





       //initview end
    }

    private void generateInitialLineData() {
        StoreData storeData = StoreData.getInstance();
        int numValues = storeData.getDate().size(); // x갯수
//        int numValues = 7;
//        makeArray();

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<PointValue> values = new ArrayList<PointValue>();
        for(int i = 0 ; i < numValues; ++i){
            values.add(new PointValue(i,storeData.getCalorie().get(i)));
            axisValues.add(new AxisValue(i).setLabel(storeData.getDate().get(i).substring(3)));
        }

        Line line = new Line(values);
        line.setColor(ChartUtils.COLOR_ORANGE).setCubic(true);


        line.setHasLabels(true);  // 그래프 y값 표시

        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        lineData = new LineChartData(lines);
        lineData.setAxisXBottom(new Axis(axisValues).setHasLines(true));
        lineData.setAxisYLeft(new Axis().setHasLines(true).setMaxLabelChars(3));


        chartTop.setLineChartData(lineData);
        chartTop.setViewportCalculationEnabled(true);

        Viewport v = new Viewport(0,50,6,0);
        Viewport v1 = new Viewport(0,110,4,0);
        v.bottom=0;
        v.top=100;
        v.left=0;
        v.right=7-1;
        chartTop.setMaximumViewport(v);
        chartTop.setCurrentViewport(v);
        chartTop.setScrollEnabled(false);
        chartTop.setZoomEnabled(false);
        chartTop.refreshDrawableState();
    }
}
