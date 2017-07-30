package kssproject.com.smproject.Presenter.DataGraph;

import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by b3216 on 2017-07-31.
 */

public interface DataGraphPresenter {
    void setView(DataGraphPresenter.View view);

    void checkChange(String item,LineChartView chart);

    public interface View {
        void graphView(Viewport max,Viewport cur);
    }
}
