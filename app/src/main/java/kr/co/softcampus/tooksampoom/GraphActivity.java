package kr.co.softcampus.tooksampoom;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
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

    private void setPushUpChart(RecordInfo[] recordInfo){
        lineChart.invalidate();
        int i=0;
        ArrayList<Entry> Entry = new ArrayList<>();

        for (RecordInfo recordinfo : recordInfo) {
            Date to = new Date();
            SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
            try {
                to = fm.parse(recordinfo.date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            float date_long = (float)to.getTime();
            int push_up = recordinfo.push_up;
            Entry.add(new Entry(date_long, push_up));
        }


        LineDataSet lineDataSet = new LineDataSet(Entry, "팔굽혀펴기");

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet); // add the data sets

        LineData data = new LineData(dataSets);

        lineDataSet.setColor(Color.BLACK);
        lineDataSet.setCircleColor(Color.BLACK);

        lineChart.setData(data);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter());
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(10);

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
    }

    private void setRunningChart(RecordInfo[] recordInfo){
        lineChart.invalidate();
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
    }
    public void onClickPushUp(View view){
        setRunningChart(recordInfo_push_up);
    }

    public void onClickSitUp(View view){
        setSitUpChart(recordInfo_sit_up);
    }

    public void onClickRunning(View view){
        setPushUpChart(recordInfo_running);
    }

}