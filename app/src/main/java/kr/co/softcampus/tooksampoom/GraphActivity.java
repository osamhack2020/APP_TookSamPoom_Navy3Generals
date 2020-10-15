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
    RecordInfo[] recordInfo1;
    RecordInfo[] recordInfo2;
    RecordInfo[] recordInfo3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        lineChart = (LineChart) findViewById(R.id.chart);
        lineChart.invalidate();
        recordInfo1 = DBhelper.getPushUpRecord(this, 1);
        recordInfo2 = DBhelper.getSitUpRecord(this, 1);
        recordInfo3 = DBhelper.getRunningRecord(this, 1);

        ArrayList<Entry> pushUpEntry = new ArrayList<>();
        ArrayList<Entry> sitUpEntry = new ArrayList<>();
        ArrayList<Entry> runningEntry = new ArrayList<>();

        for (RecordInfo recordinfo : recordInfo1) {
            Date to = new Date();
            SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
            try {
                to = fm.parse(recordinfo.date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long date_long = to.getTime();
            int push_up = Integer.parseInt(recordinfo.push_up);
            pushUpEntry.add(new Entry(date_long, push_up));
        }

        for (RecordInfo recordinfo : recordInfo2) {
            Date to = new Date();
            SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
            try {
                to = fm.parse(recordinfo.date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long date_long = to.getTime();
            int sit_up = Integer.parseInt(recordinfo.sit_up);
            sitUpEntry.add(new Entry(date_long, sit_up));
        }

        for (RecordInfo recordinfo : recordInfo3) {
            Date to = new Date();
            SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
            try {
                to = fm.parse(recordinfo.date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long date_long = to.getTime();
            int running = Integer.parseInt(recordinfo.running);
            runningEntry.add(new Entry(date_long, running));
        }

        LineDataSet lineDataSet_pushUp = new LineDataSet(pushUpEntry, "팔굽혀펴기");
        lineDataSet_pushUp.setLineWidth(2);
        lineDataSet_pushUp.setCircleRadius(6);
        lineDataSet_pushUp.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet_pushUp.setCircleColorHole(Color.BLUE);
        lineDataSet_pushUp.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet_pushUp.setDrawCircleHole(true);
        lineDataSet_pushUp.setDrawCircles(true);
        lineDataSet_pushUp.setDrawValues(false);

        LineData lineData = new LineData(lineDataSet_pushUp);
        lineChart.setData(lineData);

        MyXAxisValueFormatter xAxisformatter = new MyXAxisValueFormatter();
        XAxis xAxis = lineChart.getXAxis();
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


    }
}
/*
    public void onClickPushUp(View view) {

        ArrayList<Entry> pushUpEntry = new ArrayList<>();
        for (int i = 0; i < pushUpEntry.size(); i++) {
            pushUpEntry.add(new Entry(Integer.parseInt(recordInfo[i].date), Integer.parseInt(recordInfo[i].push_up)));
        }

        LineDataSet lineDataSet_pushUp = new LineDataSet(pushUpEntry, "팔굽혀펴기");
        lineDataSet_pushUp.setLineWidth(2);
        lineDataSet_pushUp.setCircleRadius(6);
        lineDataSet_pushUp.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet_pushUp.setCircleColorHole(Color.BLUE);
        lineDataSet_pushUp.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet_pushUp.setDrawCircleHole(true);
        lineDataSet_pushUp.setDrawCircles(true);
        lineDataSet_pushUp.setDrawValues(false);

        LineData lineData = new LineData(lineDataSet_pushUp);
        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();
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

    public void onClickSitUp(View view) {

        ArrayList<Entry> sitUpEntry = new ArrayList<>();
        for (int i = 0; i < sitUpEntry.size(); i++) {
            sitUpEntry.add(new Entry(Integer.parseInt(recordInfo[i].date), Integer.parseInt(recordInfo[i].sit_up)));
        }

        LineDataSet lineDataSet_sitUp = new LineDataSet(sitUpEntry, "윗몸일으키기");
        lineDataSet_sitUp.setLineWidth(2);
        lineDataSet_sitUp.setCircleRadius(6);
        lineDataSet_sitUp.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet_sitUp.setCircleColorHole(Color.BLUE);
        lineDataSet_sitUp.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet_sitUp.setDrawCircleHole(true);
        lineDataSet_sitUp.setDrawCircles(true);
        lineDataSet_sitUp.setDrawValues(false);

        LineData lineData = new LineData(lineDataSet_sitUp);
        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();
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

    public void onClickRunning(View view) {

        ArrayList<Entry> runningEntry = new ArrayList<>();
        for (int i = 0; i < runningEntry.size(); i++) {
            runningEntry.add(new Entry(Integer.parseInt(recordInfo[i].date), Integer.parseInt(recordInfo[i].running)));
        }

        LineDataSet lineDataSet_running = new LineDataSet(runningEntry, "3km 달리기");
        lineDataSet_running.setLineWidth(2);
        lineDataSet_running.setCircleRadius(6);
        lineDataSet_running.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet_running.setCircleColorHole(Color.BLUE);
        lineDataSet_running.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet_running.setDrawCircleHole(true);
        lineDataSet_running.setDrawCircles(true);
        lineDataSet_running.setDrawValues(false);

        LineData lineData = new LineData(lineDataSet_running);
        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);

        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("Running");

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDescription(description);
        lineChart.invalidate();
    }
}
*/
