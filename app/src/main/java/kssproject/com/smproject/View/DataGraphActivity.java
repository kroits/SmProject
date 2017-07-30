package kssproject.com.smproject.View;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import kssproject.com.smproject.Presenter.DataGraph.DataGraphPresenter;
import kssproject.com.smproject.Presenter.DataGraph.DataGraphPresenterImpl;
import kssproject.com.smproject.R;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class DataGraphActivity extends AppCompatActivity implements DataGraphPresenter.View{
    private DataGraphPresenter dataGraphPresenter;
    private LineChartView chart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alldata);
        dataGraphPresenter = new DataGraphPresenterImpl(this);
        dataGraphPresenter.setView(this);

        chart = (LineChartView) findViewById(R.id.chart);
        RadioGroup rg = (RadioGroup)findViewById(R.id.radiogroup);

        dataGraphPresenter.checkChange("calorie",chart);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                int checkedid=checkedId;
                itemCheck(checkedid);

            }
        });
    }

    private void itemCheck(int item) {
        if(item == R.id.caloriebutton){
            dataGraphPresenter.checkChange("calorie",chart);
        }
        else{
            dataGraphPresenter.checkChange("weight",chart);
        }
    }

    @Override
    public void graphView(Viewport maxViewport,Viewport curViewport) {
        chart.setMaximumViewport(maxViewport);
        chart.setCurrentViewport(curViewport);
        chart.setScrollEnabled(true);
        chart.setZoomEnabled(false);
    }

}
