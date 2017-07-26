package kssproject.com.smproject;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import kssproject.com.smproject.MidStore.StoreData;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

public class DataActivity extends AppCompatActivity {
    private LineChartView chart;
    private LineChartData lineData;
    private StoreData storeData = StoreData.getInstance();
    private int numValues = storeData.getDate().size();
    private int startleft = storeData.getDate().size()-7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alldata);

        initview();


    }

    private void initview() {

        chart = (LineChartView) findViewById(R.id.chart);
        RadioGroup rg = (RadioGroup)findViewById(R.id.radiogroup);
        generateCalorieGraph();

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                String result;
                if(checkedId == R.id.caloriebutton){
                    generateCalorieGraph();
                }
                else{
                    generateWeightGraph();
                }
            }
        });


//        RadioButton caloriebutton;
//        RadioButton weightbutton;
//
//
//        caloriebutton = (RadioButton)findViewById(R.id.caloriebutton);
//        weightbutton = (RadioButton)findViewById(R.id.weightbutton);



    }

    private void generateWeightGraph() {



        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<PointValue> values = new ArrayList<PointValue>();
        for(int i = 0 ; i < numValues; ++i){
            values.add(new PointValue(i,storeData.getWeight().get(i).floatValue()));
            axisValues.add(new AxisValue(i).setLabel(storeData.getDate().get(i).substring(5)));
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
        lineData.setAxisYLeft(new Axis().setName("weight [kg]").setHasLines(true).setMaxLabelChars(5));

        chart.setLineChartData(lineData);

        Viewport testv1 = chart.getMaximumViewport();
        testv1.top=testv1.top+5;
        testv1.bottom = testv1.bottom-3;
//        testv.set(testv.left,testv.top,testv.right+1,testv.bottom);
        Viewport v1 = new Viewport(chart.getMaximumViewport());
        if(startleft > 0) {
            v1.left = startleft;
        }

//        v1.left=startleft;
//        v1.top=70;

        v1.right=v1.right+1;
//            v.left=(float)startleft;



//        v.bottom=0;
//        v.top=3000;
//        v.left=0;
//        v.right=7;
        chart.setMaximumViewport(testv1);
        chart.setCurrentViewport(v1);
        chart.setScrollEnabled(true);
        chart.setZoomEnabled(false);
//        chart.refreshDrawableState();
    }

    private void generateCalorieGraph() {

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<PointValue> values = new ArrayList<PointValue>();
        for(int i = 0 ; i < numValues; ++i){
            values.add(new PointValue(i,storeData.getCalorie().get(i)));
            axisValues.add(new AxisValue(i).setLabel(storeData.getDate().get(i).substring(5)));
        }

//        Axis calAxis = new Axis(axisValues).setName("Calories [kcal]").setHasLines(true).setMaxLabelChars(5);
//        for(int i = 0; i < 7; ++i){
//            values.add(new PointValue(i,0));
//
//        }

        Line line = new Line(values);
        line.setColor(ChartUtils.COLOR_BLUE).setCubic(true);


        line.setHasLabels(true);  // 그래프 y값 표시

        List<Line> lines = new ArrayList<Line>();
        line.setStrokeWidth(3);
        lines.add(line);

        lineData = new LineChartData(lines);
        lineData.setAxisXBottom(new Axis(axisValues).setHasLines(true));
        lineData.setAxisYLeft(new Axis().setName("Cal [kcal]").setHasLines(true).setMaxLabelChars(3));

        chart.setLineChartData(lineData);

        Viewport testv = chart.getMaximumViewport();

        testv.top = testv.top+550;
        testv.bottom = testv.bottom - 190;

//        testv.set(testv.left,testv.top,testv.right+1,testv.bottom);
        testv.right = testv.right+1;
        Viewport v = new Viewport(chart.getMaximumViewport());
        if(startleft > 0) {
            v.left = startleft;
        }

//        v.left=startleft;
//        v.bottom=testv.bottom;


//        v.bottom=0;
//        v.top=3000;
//        v.left=0;
//        v.right=7;
        chart.setMaximumViewport(testv);
        chart.setCurrentViewport(v);
        chart.setScrollEnabled(true);
        chart.setZoomEnabled(false);
//        chart.refreshDrawableState();
    }


}
