package kssproject.com.smproject.Presenter.DataGraph;

import android.app.Activity;

import kssproject.com.smproject.Model.DataGraphModel;
import kssproject.com.smproject.R;

/**
 * Created by b3216 on 2017-07-31.
 */

public class DataGraphPresenterImpl implements DataGraphPresenter {
    private Activity activity;
    private DataGraphPresenter.View view;
    private DataGraphModel dataGraphModel;

    public DataGraphPresenterImpl(Activity activity) {
        this.activity = activity;
        this.dataGraphModel = new DataGraphModel();
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void initGraph() {
        view.calorieGraphView(dataGraphModel.calorieGraphData());
        view.weightGraphView(dataGraphModel.weightGraphData());
        view.itemCheck(R.id.caloriebutton);
    }
}
