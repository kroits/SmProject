package kssproject.com.smproject.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import kssproject.com.smproject.Presenter.Main.MainPresenter;
import kssproject.com.smproject.Presenter.Main.MainPresenterImpl;
import kssproject.com.smproject.R;
import kssproject.com.smproject.utils.Key;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class MainActivity extends AppCompatActivity implements MainPresenter.View{

    private MainPresenter mainPresenter;

    private String TAG = "kss";
    private Button button;
    private Button button2;
    private EditText testText;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();


    //// TODO: 2017-07-25  graph

    private LineChartView chart;
    private String key = Key.getInstance().getKey();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenterImpl(this);
        mainPresenter.setView(this);

        button = (Button) findViewById(R.id.button2);
        button2 = (Button)findViewById(R.id.buttonBurn);
        chart = (LineChartView) findViewById(R.id.chart_top);
        testText = (EditText)findViewById(R.id.editText);
//        mainPresenter.changeGraphData();
        databaseReference.child(key).child("information").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mainPresenter.changeGraphData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        databaseReference.child(key).child("information").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                mainPresenter.changeGraphData();
////                mainPresenter.changeGraph();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                mainPresenter.changeGraphData();
//                mainPresenter.changeGraph();
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                mainPresenter.changeGraphData();
//                mainPresenter.changeGraph();
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//                mainPresenter.changeGraphData();
//                mainPresenter.changeGraph();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.clickedAllData();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    //// TODO: 2017-07-25  graph

    @Override
    public void graphView(LineChartData lineData) {
        chart.setLineChartData(lineData);
        Viewport calorieView = chart.getMaximumViewport();
        calorieView.set(calorieView.left, calorieView.top, calorieView.right + 1, 0);
        calorieView.top = calorieView.top+400;
        chart.setMaximumViewport(calorieView);
        chart.setCurrentViewport(calorieView);
        chart.setZoomEnabled(false);
    }

    @Override
    public void allDataView(Class activity) {
        Intent DataIntent = new Intent(this,activity);
        startActivity(DataIntent);
    }
}
