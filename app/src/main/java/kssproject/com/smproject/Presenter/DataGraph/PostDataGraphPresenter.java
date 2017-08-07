package kssproject.com.smproject.Presenter.DataGraph;

import lecho.lib.hellocharts.model.LineChartData;

/**
 * Created by b3216 on 2017-08-07.
 */

public interface PostDataGraphPresenter {

    void setView(PostDataGraphPresenter.View view);

    void initGraph();

    void selectDate(int year, int month);



    public interface View {
        void itemCheck(int item);
        void weightGraphDateSelectView(LineChartData lineData);
        void calorieGraphDateSelectView(LineChartData lineData);
    }

}
