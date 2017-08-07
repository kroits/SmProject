package kssproject.com.smproject.View;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

import kssproject.com.smproject.Presenter.DataGraph.DataGraphPresenter;
import kssproject.com.smproject.Presenter.DataGraph.DataGraphPresenterImpl;
import kssproject.com.smproject.R;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class DataGraphActivity extends AppCompatActivity implements DataGraphPresenter.View {
    private DataGraphPresenter dataGraphPresenter;
    private LineChartView chartCalorie;
    private LineChartView chartWeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recentdata);

        dataGraphPresenter = new DataGraphPresenterImpl(this);
        dataGraphPresenter.setView(this);

        RadioGroup rg = (RadioGroup) findViewById(R.id.radiogroup);
        chartCalorie = (LineChartView) findViewById(R.id.chartCalorie);
        chartWeight = (LineChartView) findViewById(R.id.chartWeight);
        dataGraphPresenter.initGraph();

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                int checkedid = checkedId;
                itemCheck(checkedid);

            }
        });


    }

    @Override
    public void itemCheck(int item) {
        if (item == R.id.caloriebutton) {
            chartCalorie.setVisibility(View.VISIBLE);
            chartWeight.setVisibility(View.INVISIBLE);
        } else {
            chartCalorie.setVisibility(View.INVISIBLE);
            chartWeight.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void weightGraphDateSelectView(LineChartData lineData) {

    }

    @Override
    public void calorieGraphView(LineChartData lineData) {

        chartCalorie.setLineChartData(lineData);

        chartCalorie.setViewportCalculationEnabled(false);
        chartCalorie.setScrollEnabled(true);
        chartCalorie.setZoomEnabled(false);

        Viewport maxViewport = chartCalorie.getMaximumViewport();
        maxViewport.top = maxViewport.top + 550;

        maxViewport.right = maxViewport.right + 1;

        chartCalorie.setMaximumViewport(maxViewport);

        Viewport curViewport = new Viewport(chartCalorie.getMaximumViewport());

        if (maxViewport.right > 7) {
            curViewport.left = maxViewport.right -7;
        }


        chartCalorie.setCurrentViewport(curViewport);

    }

    @Override
    public void weightGraphView(LineChartData lineData) {
        chartWeight.setLineChartData(lineData);

        chartWeight.setViewportCalculationEnabled(false);
        chartWeight.setScrollEnabled(true);
        chartWeight.setZoomEnabled(false);

        Viewport maxViewport = chartWeight.getMaximumViewport();
        maxViewport.top = maxViewport.top + 15;

        maxViewport.right = maxViewport.right + 1;

        maxViewport.bottom = 0;

        chartWeight.setMaximumViewport(maxViewport);

        Viewport curViewport = new Viewport(chartWeight.getMaximumViewport());

        if (maxViewport.right > 7) {
            curViewport.left = maxViewport.right -7;
        }


        chartWeight.setCurrentViewport(curViewport);

    }

}
