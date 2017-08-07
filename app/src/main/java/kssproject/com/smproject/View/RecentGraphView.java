package kssproject.com.smproject.View;

import android.content.Context;
import android.util.AttributeSet;

import kssproject.com.smproject.Presenter.DataGraph.DataGraphPresenter;
import lecho.lib.hellocharts.listener.DummyLineChartOnValueSelectListener;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.ChartData;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.view.AbstractChartView;

/**
 * Created by b3216 on 2017-08-04.
 */

public class RecentGraphView extends AbstractChartView implements DataGraphPresenter{
    private static final String TAG = "RecentGraphView";
    protected LineChartData data;
    protected LineChartOnValueSelectListener onValueTouchListener = new DummyLineChartOnValueSelectListener();

    public RecentGraphView(Context context) {
         this(context,null,0);
    }

    public RecentGraphView(Context context, AttributeSet attrs) {this(context, attrs,0);
    }

    public RecentGraphView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }



    @Override
    public void setView(View view) {

    }

    @Override
    public void initGraph() {

    }

    @Override
    public ChartData getChartData() {
        return null;
    }

    @Override
    public void callTouchListener() {

    }
}
