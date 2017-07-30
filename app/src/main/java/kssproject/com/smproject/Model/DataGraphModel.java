package kssproject.com.smproject.Model;

import java.util.ArrayList;
import java.util.List;

import kssproject.com.smproject.utils.StoreData;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by b3216 on 2017-07-31.
 */

public class DataGraphModel {
    private LineChartData lineData;
    private StoreData storeData = StoreData.getInstance();
    private int numValues = storeData.getDate().size();
    private int startleft = storeData.getCalorie().size() - 7;


    public LineChartView weightGraphData(LineChartView chart) {

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<PointValue> values = new ArrayList<PointValue>();
        for (int i = 0; i < numValues; ++i) {
            values.add(new PointValue(i, storeData.getWeight().get(i).floatValue()));
            axisValues.add(new AxisValue(i).setLabel(storeData.getDate().get(i).substring(5)));
        }

        Line line = new Line(values);
        line.setColor(ChartUtils.COLOR_ORANGE).setCubic(false);
        line.setHasLabels(true);  // 그래프 y값 표시

        List<Line> lines = new ArrayList<Line>();
        line.setStrokeWidth(3);
        lines.add(line);

        lineData = new LineChartData(lines);
        lineData.setAxisXBottom(new Axis(axisValues).setHasLines(true));
        lineData.setAxisYLeft(new Axis().setName("weight [kg]").setHasLines(true).setMaxLabelChars(3));

        chart.setLineChartData(lineData);

        return chart;
    }

    public LineChartView calorieGraphData(LineChartView chart) {

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<PointValue> values = new ArrayList<PointValue>();
        for(int i = 0 ; i < numValues; ++i){
            values.add(new PointValue(i,storeData.getCalorie().get(i)));
            axisValues.add(new AxisValue(i).setLabel(storeData.getDate().get(i).substring(5)));
        }

        Line line = new Line(values);
        line.setColor(ChartUtils.COLOR_BLUE).setCubic(false);

        line.setHasLabels(true);  // 그래프 y값 표시

        List<Line> lines = new ArrayList<Line>();
        line.setStrokeWidth(3);
        lines.add(line);

        lineData = new LineChartData(lines);
        lineData.setAxisXBottom(new Axis(axisValues).setHasLines(true));
        lineData.setAxisYLeft(new Axis().setName("Cal [kcal]").setHasLines(true).setMaxLabelChars(4));

        chart.setLineChartData(lineData);

        return chart;
        }

    public Viewport settingWeightGraphMaxLine(Viewport maxViewport) {


        if (maxViewport.top > 15)
            maxViewport.top = maxViewport.top + 5;
        else
            maxViewport.top = maxViewport.top + 20;

        if (maxViewport.right < 7)
            maxViewport.right = maxViewport.right + 1;

        return maxViewport;
    }
    public Viewport settingWeightGraphCurLine(Viewport currentViewport) {

        if (startleft > 0) {
            currentViewport.left = startleft;
        }
        return currentViewport;
    }
    public Viewport settingCalorieGraphMaxLine(Viewport maxViewport) {

        maxViewport.top = maxViewport.top+550;
        if(maxViewport.right <7)
            maxViewport.right = maxViewport.right+1;

       return maxViewport;

    }

    public Viewport settingCalorieGraphcurLine(Viewport currentViewport) {

        if (startleft > 0) {
            currentViewport.left = startleft;
        }
        return currentViewport;
    }

}
