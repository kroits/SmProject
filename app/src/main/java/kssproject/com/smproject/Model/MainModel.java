package kssproject.com.smproject.Model;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

import kssproject.com.smproject.FireBase.SelectDb;
import kssproject.com.smproject.View.DataGraphActivity;
import kssproject.com.smproject.View.PostDataGraphActivity;
import kssproject.com.smproject.utils.StoreData;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.util.ChartUtils;

/**
 * Created by b3216 on 2017-07-31.
 */

public class MainModel {

    public Class graphAllData(){
        return DataGraphActivity.class;
    }

    public Class exercieseView(){
        return PostDataGraphActivity.class;
    }

    public Class graphRecenData() { return DataGraphActivity.class;}

//    public Class profileUpdate(){return Profile}
//    public Class exerciseActivity(){
//        return ExerciseActivity.class;
//    }

    public LineChartData generateInitialLineData() {
        LineChartData lineData;
        StoreData storeData = StoreData.getInstance();
        int numValues = 0;

        if (storeData.getWeight().size() > 7) {
            numValues = storeData.getWeight().size() - 7;
        } else {
        }

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<PointValue> values = new ArrayList<PointValue>();
        for (int i = 0, j = numValues; j < storeData.getWeight().size(); ++i, j++) {
            values.add(new PointValue(i, storeData.getCalorie().get(j)));
            axisValues.add(new AxisValue(i).setLabel(storeData.getDate().get(j).substring(5)));
        }

        Line line = new Line(values);
        line.setColor(ChartUtils.COLOR_ORANGE).setCubic(false);


        line.setHasLabels(true);  // 그래프 y값 표시

        List<Line> lines = new ArrayList<Line>();
        line.setStrokeWidth(3);
        lines.add(line);
        lineData = new LineChartData(lines);
        lineData.setAxisXBottom(new Axis(axisValues).setHasLines(true));
        lineData.setAxisYLeft(new Axis().setName("Cal [kcal]").setHasLines(true).setMaxLabelChars(4));
        return lineData;
    }

    public void changeGraphData(DataSnapshot dataSnapshot){
        SelectDb.getInstance().SelectData(dataSnapshot);
    }



}
