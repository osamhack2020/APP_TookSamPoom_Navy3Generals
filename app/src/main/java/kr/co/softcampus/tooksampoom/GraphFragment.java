package kr.co.softcampus.tooksampoom;

import android.database.sqlite.SQLiteDatabase;
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
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

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
    MaterialButtonToggleGroup toggleGroup;
    MaterialButton pushUpBtn;
    MaterialButton sitUpBtn;
    MaterialButton runningBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_graph,container, false);
        pushUpBtn = view.findViewById(R.id.pushupBtn);
        sitUpBtn = view.findViewById(R.id.situpBtn);
        runningBtn = view.findViewById(R.id.runBtn);
        toggleGroup = view.findViewById(R.id.toggleGroup);
        toggleGroup.setSingleSelection(true);
        toggleGroup.setSelectionRequired(true);
        lineChart = (LineChart)view.findViewById(R.id.chart);
        recordInfo_push_up = DBhelper.getPushUpRecord(getActivity(), 1);
        recordInfo_sit_up = DBhelper.getSitUpRecord(getActivity(), 1);
        recordInfo_running = DBhelper.getRunningRecord(getActivity(), 1);

        toggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                switch(checkedId){
                    case R.id.pushupBtn:
                        setPushUpChart(recordInfo_push_up);
                        break;
                    case R.id.situpBtn:
                        setSitUpChart(recordInfo_sit_up);
                        break;
                    case R.id.runBtn:
                        setRunningChart(recordInfo_running);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + checkedId);
                }
            }
        });
        toggleGroup.check(R.id.pushupBtn);

        return view;
    }

    private void setPushUpChart(RecordInfo[] recordInfo){
        lineChart.invalidate();
        int j=0;
        ArrayList<Entry> Entry = new ArrayList<>();
        int max =0;
        int min =10000;
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
            if(max<push_up){
                max = push_up;
            }
            if(min>push_up){
                min = push_up;
            }
            j++;
        }
        LineDataSet lineDataSet = new LineDataSet(Entry, "팔굽혀펴기");

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        LineData data = new LineData(dataSets);

        lineDataSet.setColor(Color.BLACK);
        lineDataSet.setCircleColor(Color.rgb(40,104,176));
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleRadius(5f);
        lineDataSet.setValueTextSize(10f);

        lineChart.setData(data);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter(values));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(recordInfo_push_up.length-1);

        YAxis yAxisLeft = lineChart.getAxisLeft(); //Y축의 왼쪽면 설정
        yAxisLeft.setAxisMaximum(max);
        yAxisLeft.setAxisMinimum(min);
        yAxisLeft.setLabelCount(max-min+1,true);
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
        int max=0;
        int min=10000;
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
            if(max<sit_up){
                max = sit_up;
            }
            if(min>sit_up){
                min = sit_up;
            }
            j++;
        }
        LineDataSet lineDataSet = new LineDataSet(Entry, "윗몸일으키기");

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        LineData data = new LineData(dataSets);

        lineDataSet.setColor(Color.BLACK);
        lineDataSet.setCircleColor(Color.rgb(40,104,176));
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleRadius(5f);
        lineDataSet.setValueTextSize(10f);

        lineChart.setData(data);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter(values));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(recordInfo_sit_up.length-1);

        YAxis yAxisLeft = lineChart.getAxisLeft(); //Y축의 왼쪽면 설정
        yAxisLeft.setAxisMaximum(max);
        yAxisLeft.setAxisMinimum(min);
        yAxisLeft.setLabelCount(max-min+1,true);
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
        int max=-10000;
        int min=0;
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
            if(max<-1*running){
                max = -1*running;
            }
            if(min>-1*running){
                min = -1*running;
            }
            j++;
        }
        LineDataSet lineDataSet = new LineDataSet(Entry, "3km 달리기");

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        LineData data = new LineData(dataSets);

        lineDataSet.setColor(Color.BLACK);
        lineDataSet.setCircleColor(Color.rgb(40,104,176));
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleRadius(5f);
        lineDataSet.setValueTextSize(10f);
        lineDataSet.setValueFormatter(new MyIValueFormatter());
        lineChart.setData(data);


        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter(values));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(recordInfo_running.length-1);

        YAxis yAxisLeft = lineChart.getAxisLeft(); //Y축의 왼쪽면 설정
        yAxisLeft.setAxisMaximum(max);
        yAxisLeft.setAxisMinimum(min);
        yAxisLeft.setLabelCount(max-min+1,true);
        yAxisLeft.setValueFormatter(new MyYAxisValueFormatter());

        YAxis yAxisRight = lineChart.getAxisRight(); //Y축의 오른쪽면 설정
        yAxisRight.setDrawLabels(false);
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawGridLines(false);
    }
    public void setDummyData(){
        for(int j=1; j<5; j++){
            int push_up = 50;
            int sit_up =60;
            int running = 800;
            for(int i=10; i<24; i++){
                if(i<=17){
                    push_up += i%3;
                    sit_up += i%4;
                    running -= i%7;
                }
                else{
                    push_up += i%4;
                    sit_up += i%3;
                    running -= i%8;
                }
                RecordInfo recordinfo = new RecordInfo();
                recordinfo.setId(j);
                recordinfo.setPushup(push_up);
                recordinfo.setRunning(running);
                recordinfo.setSitup(sit_up);

                DBhelper helper = new DBhelper(getActivity());
                SQLiteDatabase db = helper.getWritableDatabase();

                String sql1 = "INSERT INTO Record_Push_Up(id,"
                        +"push_up,"
                        +"date) VALUES(?,?,?)";
                String date1 = "2020-10-"+i+" 20:10:43";
                String[] value1 = {Integer.toString(j),
                        Integer.toString(recordinfo.push_up),
                        date1};
                db.execSQL(sql1,value1);

                String sql2 = "INSERT INTO Record_Sit_Up(id,"
                        +"sit_up,"
                        +"date) VALUES(?,?,?)";
                String date2 = "2020-10-"+i+" 20:10:43";
                String[] value2 = {Integer.toString(j),
                        Integer.toString(recordinfo.sit_up),
                        date2};
                db.execSQL(sql2,value2);

                String sql3 = "INSERT INTO Record_Running(id,"
                        +"running,"
                        +"date) VALUES(?,?,?)";
                String date3 = "2020-10-"+i+" 20:10:43";
                String[] value3 = {Integer.toString(j),
                        Integer.toString(recordinfo.running),
                        date3};
                db.execSQL(sql3,value3);

                db.close();
            }
        }
    }
}