package kssproject.com.smproject.View;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import kssproject.com.smproject.Presenter.DataGraph.PostDataGraphPresenter;
import kssproject.com.smproject.Presenter.DataGraph.PostDataGraphPresenterImpl;
import kssproject.com.smproject.R;
import kssproject.com.smproject.utils.DateUtil;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class PostDataGraphActivity extends AppCompatActivity implements PostDataGraphPresenter.View {
    private PostDataGraphPresenter postDataGraphPresenter;
    private LineChartView chartCalorie;
    private LineChartView chartWeight;
    private ImageButton yearUpButton,yearDownButton;
    private ImageButton monthUpButton, monthDownButton;
    private TextView yearView,monthView;
    private Button viewButton;
    private int yearValue= DateUtil.getInstance().getDate().getYear()+1900;
    private int monthValue= DateUtil.getInstance().getDate().getMonth()+1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_data_graph);

        postDataGraphPresenter = new PostDataGraphPresenterImpl(this);

        postDataGraphPresenter.setView(this);

        yearUpButton = (ImageButton)findViewById(R.id.yearUpButton);
        yearDownButton = (ImageButton)findViewById(R.id.yearDownButton);
        yearView = (TextView)findViewById(R.id.yearView);
        yearView.setText(String.valueOf(yearValue));

        monthUpButton = (ImageButton)findViewById(R.id.monthUpButton);
        monthDownButton = (ImageButton)findViewById(R.id.monthDownButton);
        monthView= (TextView)findViewById(R.id.monthView);
        monthView.setText(String.valueOf(monthValue));

        RadioGroup rg = (RadioGroup) findViewById(R.id.radiogroup);
        chartCalorie = (LineChartView) findViewById(R.id.chartCalorie);
        chartWeight = (LineChartView) findViewById(R.id.chartWeight);
        postDataGraphPresenter.initGraph();
        Button sign = (Button)findViewById(R.id.viewButton);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                int checkedid = checkedId;
                itemCheck(checkedid);

            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year,month;
                year = Integer.parseInt(yearView.getText().toString());
                month = Integer.parseInt(monthView.getText().toString());

                postDataGraphPresenter.selectDate(year,month);
            }
        });

        yearUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                yearUp();
            }
        });

        yearDownButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                yearDown();
            }
        });

        monthUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                monthUp();
            }
        });

        monthDownButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                monthDown();
            }
        });

        viewButton = (Button)findViewById(R.id.viewButton);


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
        chartWeight.setLineChartData(lineData);

        chartWeight.setViewportCalculationEnabled(true);
        chartWeight.setScrollEnabled(true);
        chartWeight.setZoomEnabled(false);

        Viewport maxViewport = chartWeight.getMaximumViewport();
        maxViewport.top = maxViewport.top + 15;

        maxViewport.right = maxViewport.right + 1;

        maxViewport.bottom = 0;

        chartWeight.setMaximumViewport(maxViewport);

        Viewport curViewport = new Viewport(chartWeight.getMaximumViewport());

        if (maxViewport.right > 7) {
            curViewport.left = maxViewport.right - 7;
        }


        chartWeight.setCurrentViewport(curViewport);

    }

    @Override
    public void calorieGraphDateSelectView(LineChartData lineData) {

        chartCalorie.setLineChartData(lineData);

        chartCalorie.setViewportCalculationEnabled(true);
        chartCalorie.setScrollEnabled(true);
        chartCalorie.setZoomEnabled(false);

        Viewport maxViewport = chartCalorie.getMaximumViewport();
        maxViewport.top = maxViewport.top + 550;

        maxViewport.right = maxViewport.right + 1;
        maxViewport.bottom = 0;
        chartCalorie.setMaximumViewport(maxViewport);

        Viewport curViewport = new Viewport(chartCalorie.getMaximumViewport());

        if (maxViewport.right > 7) {
            curViewport.left = maxViewport.right - 7;
        }

        chartCalorie.setCurrentViewport(curViewport);

    }

    public void yearUp(){
        yearValue++;

        if(yearValue>2999){
            yearValue=2999;
        }

        yearView.setText(String.valueOf(yearValue));
    }
    public void yearDown(){
        yearValue--;

        if(yearValue<2017){
            yearValue=2017;
        }

        yearView.setText(String.valueOf(yearValue));
    }

    public void monthUp(){
        monthValue++;

        if(monthValue>12){
            monthValue=1;
        }

        monthView.setText(String.valueOf(monthValue));
    }
    public void monthDown(){
        monthValue--;

        if(monthValue<1){
            monthValue=12;
        }

        monthView.setText(String.valueOf(monthValue));
    }
}

