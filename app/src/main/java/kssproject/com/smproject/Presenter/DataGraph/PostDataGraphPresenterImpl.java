package kssproject.com.smproject.Presenter.DataGraph;

import android.app.Activity;

import java.util.Date;

import kssproject.com.smproject.Model.PostDataGraphModel;
import kssproject.com.smproject.R;
import kssproject.com.smproject.utils.DateUtil;

/**
 * Created by b3216 on 2017-08-07.
 */

public class PostDataGraphPresenterImpl implements PostDataGraphPresenter {
    private Activity activity;
    private PostDataGraphPresenter.View view;
    private PostDataGraphModel postDataGraphModel;
    private Date today = DateUtil.getInstance().getDate();
    private int year = (today.getYear()+1900);
    private int month = today.getMonth()+1;

    public PostDataGraphPresenterImpl(Activity activity) {
        this.activity = activity;
        this.postDataGraphModel = new PostDataGraphModel();
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void initGraph() {
        postDataGraphModel.dateConverter(year, month);

        view.calorieGraphDateSelectView(postDataGraphModel.calorieSelectDateGraph());
        view.weightGraphDateSelectView(postDataGraphModel.weightSelectDateGraph());

        view.itemCheck(R.id.caloriebutton);
    }


    @Override
    public void selectDate(int year, int month) {
        postDataGraphModel.dateConverter(year,month);

        view.calorieGraphDateSelectView(postDataGraphModel.calorieSelectDateGraph());
        view.weightGraphDateSelectView(postDataGraphModel.weightSelectDateGraph());

    }


}
