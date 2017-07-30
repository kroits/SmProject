package kssproject.com.smproject.Presenter.DataGraph;

import android.app.Activity;

import kssproject.com.smproject.Model.DataGraphModel;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by b3216 on 2017-07-31.
 */

public class DataGraphPresenterImpl implements DataGraphPresenter{
    private Activity activity;
    private DataGraphPresenter.View view;
    private DataGraphModel dataGraphModel;

    public DataGraphPresenterImpl(Activity activity){
        this.activity = activity;
        this.dataGraphModel = new DataGraphModel();

    }

    @Override
    public void setView(View view) {this.view = view;}

    @Override
    public void checkChange(String item,LineChartView chart) {
        if(item.equals("calorie")){
            chart = dataGraphModel.calorieGraphData(chart);
            view.graphView(dataGraphModel.settingCalorieGraphMaxLine(chart.getMaximumViewport()),dataGraphModel.settingCalorieGraphcurLine(new Viewport(chart.getMaximumViewport())));
        }
        else{
            chart = dataGraphModel.weightGraphData(chart);
            view.graphView(dataGraphModel.settingWeightGraphMaxLine(chart.getMaximumViewport()),dataGraphModel.settingWeightGraphCurLine(new Viewport(chart.getMaximumViewport())));
        }
    }
}
