package kssproject.com.smproject.Presenter.DataGraph;

import lecho.lib.hellocharts.model.LineChartData;

/**
 * Created by b3216 on 2017-07-31.
 */

public interface DataGraphPresenter {
    void setView(DataGraphPresenter.View view);

    void initGraph();

    public interface View {
        void calorieGraphView(LineChartData lineData);
        void weightGraphView(LineChartData lineData);
        void itemCheck(int item);
    }


}
