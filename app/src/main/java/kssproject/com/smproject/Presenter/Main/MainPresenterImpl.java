package kssproject.com.smproject.Presenter.Main;

import android.app.Activity;

import com.google.firebase.database.DataSnapshot;

import kssproject.com.smproject.Model.MainModel;

/**
 * Created by b3216 on 2017-07-31.
 */

public class MainPresenterImpl implements MainPresenter{
    private Activity activity;
    private MainPresenter.View view;
    private MainModel mainModel;

    public MainPresenterImpl(Activity activity){
        this.activity = activity;
        this.mainModel = new MainModel();
    }

    @Override
    public void setView(View view) {this.view = view; }

    @Override
    public void clickedAllData() {
        view.allDataView(mainModel.graphAllData());
    }

    @Override
    public void changeGraphData(DataSnapshot dataSnapshot) {
        mainModel.changeGraphData(dataSnapshot);
        changeGraph();
    }

    @Override
    public void changeGraph() {
        view.graphView(mainModel.generateInitialLineData());
    }

    @Override
    public void clickedExercise() {

    }
}
