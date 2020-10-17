package kr.co.softcampus.tooksampoom;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GraphActivity extends AppCompatActivity {

    private LineChart lineChart;
    RecordInfo[] recordInfo_push_up;
    RecordInfo[] recordInfo_sit_up;
    RecordInfo[] recordInfo_running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        lineChart = (LineChart) findViewById(R.id.chart);

        recordInfo_push_up = DBhelper.getPushUpRecord(this, 1);
        recordInfo_sit_up = DBhelper.getSitUpRecord(this, 1);
        recordInfo_running = DBhelper.getRunningRecord(this, 1);

        setPushUpChart(recordInfo_push_up);
    }
    
    public void onClickPushUp(){
        setPushUpChart(recordInfo_push_up);
    }

    public void onClickSitUp(){
        setPushUpChart(recordInfo_sit_up);
    }

    public void onClickRunning(){
        setPushUpChart(recordInfo_running);
    }

    private void setPushUpChart(RecordInfo[] recordInfo){
        ArrayList<Entry> Entry = new ArrayList<Entry>();

        for (RecordInfo recordinfo : recordInfo) {
            Date to = new Date();
            SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                to = fm.parse(recordinfo.date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long date_long = to.getTime();
            int push_up = recordinfo.push_up;
            Entry.add(new Entry(date_long, push_up));
        }

        LineDataSet lineDataSet = new LineDataSet(Entry, "팔굽혀펴기");
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setCircleColorHole(Color.BLUE);
        lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawValues(false);

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();
        MyXAxisValueFormatter xAxisformatter = new MyXAxisValueFormatter();
        xAxis.setValueFormatter(xAxisformatter);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);

        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("PUSH_UP");

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDescription(description);
        lineChart.invalidate();
    }

    private void setSitUpChart(RecordInfo[] recordInfo){
        ArrayList<Entry> Entry = new ArrayList<Entry>();
        for (RecordInfo recordinfo : recordInfo) {
            Date to = new Date();
            SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                to = fm.parse(recordinfo.date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long date_long = to.getTime();
            int sit_up = recordinfo.sit_up;
            Entry.add(new Entry(date_long, sit_up));
        }

        LineDataSet lineDataSet = new LineDataSet(Entry, "윗몸일으키기");
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setCircleColorHole(Color.BLUE);
        lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawValues(false);

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();
        MyXAxisValueFormatter xAxisformatter = new MyXAxisValueFormatter();
        xAxis.setValueFormatter(xAxisformatter);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);

        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("SIT_UP");

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDescription(description);
        lineChart.invalidate();
    }

    private void setRunningChart(RecordInfo[] recordInfo){
        ArrayList<Entry> Entry = new ArrayList<Entry>();
        for (RecordInfo recordinfo : recordInfo) {
            Date to = new Date();
            SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                to = fm.parse(recordinfo.date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long date_long = to.getTime();
            int running = recordinfo.running;
            Entry.add(new Entry(date_long, running));
        }

        LineDataSet lineDataSet = new LineDataSet(Entry, "3km 달리기");
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setCircleColorHole(Color.BLUE);
        lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawValues(false);

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();
        MyXAxisValueFormatter xAxisformatter = new MyXAxisValueFormatter();
        xAxis.setValueFormatter(xAxisformatter);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);

        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("3km_RUNNING");

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDescription(description);
        lineChart.invalidate();
    }
}