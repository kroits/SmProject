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

    }

    private void generateWeightGraph() {

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<PointValue> values = new ArrayList<PointValue>();
        for(int i = 0 ; i < numValues; ++i){
            values.add(new PointValue(i,storeData.getWeight().get(i).floatValue()));
            axisValues.add(new AxisValue(i).setLabel(storeData.getDate().get(i).substring(5)));
        }

        Line line = new Line(values);
        line.setColor(ChartUtils.COLOR_ORANGE).setCubic(false);


        line.setHasLabels(true);  // 그래프 y값 표시

        List<Line> lines = new ArrayList<Line>();
        line.setStrokeWidth(3);
        lines.add(line);

        lineData = new LineChartData(lines);
        lineData.setAxisXBottom(new Axis(axisValues).setHasLines(true));
        lineData.setAxisYLeft(new Axis().setName("weight [kg]").setHasLines(true).setMaxLabelChars(3));

        chart.setLineChartData(lineData);

        Viewport weightMaxView = chart.getMaximumViewport();


        if(weightMaxView.top>15)
            weightMaxView.top=weightMaxView.top+5;
        else
            weightMaxView.top = weightMaxView.top+20;

        if(weightMaxView.right <7)
            weightMaxView.right = weightMaxView.right+1;

        Viewport weightCurrentView = new Viewport(chart.getMaximumViewport());

        if(startleft > 0) {
            weightCurrentView.left = startleft;
        }

        chart.setMaximumViewport(weightMaxView);
        chart.setCurrentViewport(weightCurrentView);
        chart.setScrollEnabled(true);
        chart.setZoomEnabled(false);

    }

    private void generateCalorieGraph() {

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<PointValue> values = new ArrayList<PointValue>();
        for(int i = 0 ; i < numValues; ++i){
            values.add(new PointValue(i,storeData.getCalorie().get(i)));
            axisValues.add(new AxisValue(i).setLabel(storeData.getDate().get(i).substring(5)));
        }

        Line line = new Line(values);
        line.setColor(ChartUtils.COLOR_BLUE).setCubic(false);

        line.setHasLabels(true);  // 그래프 y값 표시

        List<Line> lines = new ArrayList<Line>();
        line.setStrokeWidth(3);
        lines.add(line);

        lineData = new LineChartData(lines);
        lineData.setAxisXBottom(new Axis(axisValues).setHasLines(true));
        lineData.setAxisYLeft(new Axis().setName("Cal [kcal]").setHasLines(true).setMaxLabelChars(4));

        chart.setLineChartData(lineData);

        Viewport calorieMaxView = chart.getMaximumViewport();


        calorieMaxView.top = calorieMaxView.top+550;
        if(calorieMaxView.right <7)
            calorieMaxView.right = calorieMaxView.right+1;


        Viewport calorieCurrentView = new Viewport(chart.getMaximumViewport());
        if(startleft > 0) {
            calorieCurrentView.left = startleft;
        }

        chart.setMaximumViewport(calorieMaxView);
        chart.setCurrentViewport(calorieCurrentView);
        chart.setScrollEnabled(true);
        chart.setZoomEnabled(false);
    }

}
