package kssproject.com.smproject.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import kssproject.com.smproject.R;
import kssproject.com.smproject.utils.StoreData;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

public class GraphActivity extends ActionBarActivity implements ActionBar.TabListener {
    private static StoreData storeData = StoreData.getInstance();
    private static int numValues = storeData.getDate().size();
    private String startDate;
    private String endDate;
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            public void onPageSelectied(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            actionBar.addTab(actionBar.newTab().setText(mSectionsPagerAdapter.getPageTitle(i)).setTabListener(this));
        }

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

        mViewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {


    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_graph, container, false);
            View select = inflater.inflate(R.layout.fragment_graph,container,false);
            RelativeLayout layout = (RelativeLayout) rootView;
            RelativeLayout layout1 = (RelativeLayout) select;
            int sectionNum = getArguments().getInt(ARG_SECTION_NUMBER);
            switch (sectionNum) {
                case 1:
                    LineChartView fixDateCalorieGraphView = new LineChartView(getActivity());
                    fixDateCalorieGraphView.setLineChartData(generateFixDateCalorieDate());
                    fixDateCalorieGraphView.setZoomType(ZoomType.HORIZONTAL);
                    fixDateCalorieGraphView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
                    fixDateCalorieGraphView.setViewportCalculationEnabled(false);
                    fixDateCalorieGraphView.setScrollEnabled(true);
                    fixDateCalorieGraphView.setZoomEnabled(false);

                    Viewport maxFixCalorieViewport = fixDateCalorieGraphView.getMaximumViewport();
                    maxFixCalorieViewport.top = maxFixCalorieViewport.top + 550;

                    maxFixCalorieViewport.right = maxFixCalorieViewport.right + 1;

                    fixDateCalorieGraphView.setMaximumViewport(maxFixCalorieViewport);

                    Viewport curFixCalorieViewport = new Viewport(fixDateCalorieGraphView.getMaximumViewport());

                    if (maxFixCalorieViewport.right > 7) {
                        curFixCalorieViewport.left = maxFixCalorieViewport.right -7;
                    }


                    fixDateCalorieGraphView.setCurrentViewport(curFixCalorieViewport);

                    layout.addView(fixDateCalorieGraphView);
                    break;
                case 2:
                    LineChartView fixDateWeightGraphView = new LineChartView(getActivity());
                    fixDateWeightGraphView.setLineChartData(generateFixDateWeightDate());
                    fixDateWeightGraphView.setZoomType(ZoomType.HORIZONTAL);
                    fixDateWeightGraphView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
                    fixDateWeightGraphView.setViewportCalculationEnabled(false);
                    fixDateWeightGraphView.setScrollEnabled(true);
                    fixDateWeightGraphView.setZoomEnabled(false);

                    Viewport maxFixWeightViewport = fixDateWeightGraphView.getMaximumViewport();
                    maxFixWeightViewport.top = maxFixWeightViewport.top + 15;

                    maxFixWeightViewport.right = maxFixWeightViewport.right + 1;

                    maxFixWeightViewport.bottom = 0;

                    fixDateWeightGraphView.setMaximumViewport(maxFixWeightViewport);

                    Viewport curFixWeightViewport = new Viewport(fixDateWeightGraphView.getMaximumViewport());

                    if (maxFixWeightViewport.right > 7) {
                        curFixWeightViewport.left = maxFixWeightViewport.right -7;
                    }


                    fixDateWeightGraphView.setCurrentViewport(curFixWeightViewport);
                    layout.addView(fixDateWeightGraphView);
                    break;
            }
            return rootView;
        }

        private LineChartData generateFixDateWeightDate() {

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

        private LineChartData generateFixDateCalorieDate() {
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

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {super(fm); }

        @Override
        public Fragment getItem(int position) {

            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "최근30일";
                case 1:
                    return "이전기록";
            }
            return null;
        }
    }

}