package kr.co.softcampus.tooksampoom;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GraphFragment extends Fragment {

    private LineChart lineChart;
    RecordInfo[] recordInfo_push_up;
    RecordInfo[] recordInfo_sit_up;
    RecordInfo[] recordInfo_running;
    View view;
    Button button_1;
    Button button_2;
    Button button_3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_graph,container, false);
        button_1 = (Button)view.findViewById(R.id.pushUpBtn);
        button_2 = (Button)view.findViewById(R.id.sitUpBtn);
        button_3 = (Button)view.findViewById(R.id.runningBtn);
        lineChart = (LineChart)view.findViewById(R.id.chart);

        recordInfo_push_up = DBhelper.getPushUpRecord(getActivity(), 1);
        recordInfo_sit_up = DBhelper.getSitUpRecord(getActivity(), 1);
        recordInfo_running = DBhelper.getRunningRecord(getActivity(), 1);

        setPushUpChart(recordInfo_push_up);

        button_1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setPushUpChart(recordInfo_push_up);
            }
        });

        button_2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setSitUpChart(recordInfo_sit_up);
            }
        });

        button_3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setRunningChart(recordInfo_running);
            }
        });

        return view;
    }

    private void setPushUpChart(RecordInfo[] recordInfo){
        lineChart.invalidate();
        int j=0;
        ArrayList<Entry> Entry = new ArrayList<>();
        String[] values = new String[recordInfo.length];
        for (int i=recordInfo.length-1; i>=0; i--) {
            Date to = new Date();
            SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat fm2 = new SimpleDateFormat("MM/dd");
            try {
                to = fm1.parse(recordInfo[i].date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String date = fm2.format(to);
            int push_up = recordInfo[i].push_up;
            Entry.add(new Entry(j,push_up));
            values[j] = date;
            j++;
        }
        LineDataSet lineDataSet = new LineDataSet(Entry, "팔굽혀펴기");

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        LineData data = new LineData(dataSets);

        lineDataSet.setColor(Color.BLUE);
        lineDataSet.setCircleColor(Color.BLUE);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleRadius(4f);
        lineDataSet.setValueTextSize(9f);

        lineChart.setData(data);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter(values));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(recordInfo_push_up.length-1);

        YAxis yAxisLeft = lineChart.getAxisLeft(); //Y축의 왼쪽면 설정
        yAxisLeft.setAxisMaximum(100);
        yAxisLeft.setAxisMinimum(97);
        yAxisLeft.setLabelCount(100-97+1,true);
        yAxisLeft.setValueFormatter(null);

        YAxis yAxisRight = lineChart.getAxisRight(); //Y축의 오른쪽면 설정
        yAxisRight.setDrawLabels(false);
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawGridLines(false);

        lineChart.getLegend().setEnabled(false);
        lineChart.getDescription().setEnabled(false);
        lineChart.setDrawGridBackground(false);
    }

    private void setSitUpChart(RecordInfo[] recordInfo){
        lineChart.invalidate();
        int j=0;
        ArrayList<Entry> Entry = new ArrayList<>();
        String[] values = new String[recordInfo.length];
        for (int i=recordInfo.length-1; i>=0; i--) {
            Date to = new Date();
            SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat fm2 = new SimpleDateFormat("MM/dd");
            try {
                to = fm1.parse(recordInfo[i].date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String date = fm2.format(to);
            int sit_up = recordInfo[i].sit_up;
            Entry.add(new Entry(j,sit_up));
            values[j] = date;
            j++;
        }
        LineDataSet lineDataSet = new LineDataSet(Entry, "윗몸일으키기");

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        LineData data = new LineData(dataSets);

        lineDataSet.setColor(Color.BLUE);
        lineDataSet.setCircleColor(Color.BLUE);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleRadius(4f);
        lineDataSet.setValueTextSize(9f);

        lineChart.setData(data);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter(values));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(recordInfo_sit_up.length-1);

        YAxis yAxisLeft = lineChart.getAxisLeft(); //Y축의 왼쪽면 설정
        yAxisLeft.setAxisMaximum(113);
        yAxisLeft.setAxisMinimum(110);
        yAxisLeft.setLabelCount(113-110+1,true);
        yAxisLeft.setValueFormatter(null);
        YAxis yAxisRight = lineChart.getAxisRight(); //Y축의 오른쪽면 설정
        yAxisRight.setDrawLabels(false);
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawGridLines(false);
    }

    private void setRunningChart(RecordInfo[] recordInfo){
        lineChart.invalidate();
        int j=0;
        ArrayList<Entry> Entry = new ArrayList<>();
        String[] values = new String[recordInfo.length];
        for (int i=recordInfo.length-1; i>=0; i--) {
            Date to = new Date();
            SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat fm2 = new SimpleDateFormat("MM/dd");
            try {
                to = fm1.parse(recordInfo[i].date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String date = fm2.format(to);
            int running = recordInfo[i].running;
            Entry.add(new Entry(j,-1*running));
            values[j] = date;
            j++;
        }
        LineDataSet lineDataSet = new LineDataSet(Entry, "3km 달리기");

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        LineData data = new LineData(dataSets);

        lineDataSet.setColor(Color.BLUE);
        lineDataSet.setCircleColor(Color.BLUE);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleRadius(4f);
        lineDataSet.setValueTextSize(7f);
        lineDataSet.setValueFormatter(new MyIValueFormatter());
        lineChart.setData(data);


        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter(values));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(recordInfo_running.length-1);

        YAxis yAxisLeft = lineChart.getAxisLeft(); //Y축의 왼쪽면 설정
        yAxisLeft.setAxisMaximum(-1*750);
        yAxisLeft.setAxisMinimum(-1*753);
        yAxisLeft.setLabelCount(4,true);
        yAxisLeft.setValueFormatter(new MyYAxisValueFormatter());

        YAxis yAxisRight = lineChart.getAxisRight(); //Y축의 오른쪽면 설정
        yAxisRight.setDrawLabels(false);
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawGridLines(false);
    }
}