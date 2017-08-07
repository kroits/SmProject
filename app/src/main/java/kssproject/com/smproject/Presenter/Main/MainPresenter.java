package kssproject.com.smproject.Presenter.Main;

import com.google.firebase.database.DataSnapshot;

import lecho.lib.hellocharts.model.LineChartData;

/**
 * Created by b3216 on 2017-07-30.
 */

public interface MainPresenter {
    void setView(MainPresenter.View view);

    void clickedAllData();


    void changeGraphData(DataSnapshot dataSnapshot);
    void changeGraph();

    void clickedExercise();

    public interface View {
        void graphView(LineChartData lineData);
        void otherActivity(Class activity);
    }
}
