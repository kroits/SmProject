package kssproject.com.smproject.Model;

import java.util.ArrayList;
import java.util.List;

import kssproject.com.smproject.utils.StoreData;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.util.ChartUtils;

/**
 * Created by b3216 on 2017-08-07.
 */

public class PostDataGraphModel {
    private StoreData storeData = StoreData.getInstance();
    private int numValues = storeData.getDate().size();
    private String startDate;
    private String endDate;

    public void dateConverter(int year, int month){
        int nextMonth ;
        int nextYear;
        if(month == 12){
            nextMonth = 1;
            nextYear = year + 1;
        }
        else{
            nextMonth = month+1;
            nextYear = year;
        }
        String strYear = String.valueOf(year);
        String strMonth = String.valueOf(month);
        if(strMonth.length() <2){
            strMonth = "0".concat(strMonth);
        }
        String strNextMonth = String.valueOf(nextMonth);
        if(strNextMonth.length() < 2){
            strNextMonth = "0".concat(strNextMonth);
        }
        String strNextYear = String.valueOf(nextYear);
        String startDate = strYear.concat("-").concat(strMonth);
        String endDate = strNextYear.concat("-").concat(strNextMonth);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LineChartData calorieSelectDateGraph(){
        LineChartData lineData;
        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<PointValue> values = new ArrayList<PointValue>();
        for (int i = 0; i < numValues; ++i) {
            if(storeData.getDate().get(i).contains(startDate)) {
                values.add(new PointValue(i, storeData.getCalorie().get(i).floatValue()));
                axisValues.add(new AxisValue(i).setLabel(storeData.getDate().get(i).substring(5)));
            }
            else{
                if(storeData.getDate().get(i).contains(endDate)){
                    break;
                }
            }

        }
        Line line = new Line(values);
        line.setColor(ChartUtils.COLOR_BLUE).setCubic(false);

        line.setHasLabels(true);  // 그래프 y값 표시

        List<Line> lines = new ArrayList<Line>();
        line.setStrokeWidth(2);
        lines.add(line);

        lineData = new LineChartData(lines);
        lineData.setAxisXBottom(new Axis(axisValues).setHasLines(true).setTextSize(11));
        lineData.setAxisYLeft(new Axis().setName("Cal [kcal]").setHasLines(true).setMaxLabelChars(4));

        return lineData;
    }
    public LineChartData weightSelectDateGraph(){
        LineChartData lineData;
        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<PointValue> values = new ArrayList<PointValue>();
        for (int i = 0; i < numValues; ++i) {
            if(storeData.getDate().get(i).contains(startDate)) {
                values.add(new PointValue(i, storeData.getWeight().get(i).floatValue()));
                axisValues.add(new AxisValue(i).setLabel(storeData.getDate().get(i).substring(5)));
            }
            else{
                if(storeData.getDate().get(i).contains(endDate)){
                    break;
                }
            }

        }

        Line line = new Line(values);
        line.setColor(ChartUtils.COLOR_ORANGE).setCubic(false);
        line.setHasLabels(true);  // 그래프 y값 표시
        List<Line> lines = new ArrayList<Line>();
        line.setStrokeWidth(2);
        lines.add(line);

        lineData = new LineChartData(lines);
        lineData.setAxisXBottom(new Axis(axisValues).setHasLines(true));
        lineData.setAxisYLeft(new Axis().setName("weight [kg]").setHasLines(true).setMaxLabelChars(3));


        return lineData;
    }

    public LineChartData weightGraphData() {
        LineChartData lineData;
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
        line.setStrokeWidth(2);
        lines.add(line);

        lineData = new LineChartData(lines);
        lineData.setAxisXBottom(new Axis(axisValues).setHasLines(true));
        lineData.setAxisYLeft(new Axis().setName("weight [kg]").setHasLines(true).setMaxLabelChars(3));


        return lineData;
    }

    public LineChartData calorieGraphData() {
        LineChartData lineData;
        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<PointValue> values = new ArrayList<PointValue>();
        for (int i = 0; i < numValues; ++i) {
            values.add(new PointValue(i, storeData.getCalorie().get(i)));
            axisValues.add(new AxisValue(i).setLabel(storeData.getDate().get(i).substring(5)));
        }

        Line line = new Line(values);
        line.setColor(ChartUtils.COLOR_BLUE).setCubic(false);

        line.setHasLabels(true);  // 그래프 y값 표시

        List<Line> lines = new ArrayList<Line>();
        line.setStrokeWidth(2);
        lines.add(line);

        lineData = new LineChartData(lines);
        lineData.setAxisXBottom(new Axis(axisValues).setHasLines(true).setTextSize(11));
        lineData.setAxisYLeft(new Axis().setName("Cal [kcal]").setHasLines(true).setMaxLabelChars(4));

        return lineData;
    }

}
