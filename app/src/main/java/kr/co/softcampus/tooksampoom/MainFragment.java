package kr.co.softcampus.tooksampoom;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainFragment extends Fragment{
    View view;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button6;
    RadarChart radarChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main,container, false);
        button1 = (Button) view.findViewById(R.id.button1);
        button2 = (Button) view.findViewById(R.id.button2);
        button3 = (Button) view.findViewById(R.id.button3);
        button4 = (Button) view.findViewById(R.id.button4);
        button6 = (Button) view.findViewById(R.id.button6);


        radarChart = (RadarChart) view.findViewById((R.id.chart));


        ArrayList<RadarEntry> dataValue = new ArrayList<>();
        float a = (float)(46.0/76.0)*100;
        dataValue.add(new RadarEntry(a));
        dataValue.add(new RadarEntry((float)(54.0/86.0)*100));
        dataValue.add(new RadarEntry((float)(623.0/760.0)*100));

        RadarDataSet dataSet = new RadarDataSet(dataValue, "data");
        dataSet.setColor(Color.BLUE);
        dataSet.setColor(R.color.colorPrimary);
        dataSet.setDrawFilled(true);
        dataSet.setValueTextSize(13f);
        RadarData data = new RadarData();
        String[] values = {46+"개", 54+"개", 623/60+"분 "+623%60+"초"};
        dataSet.setValueFormatter(new MyValueFormatter(values));
        data.addDataSet(dataSet);

        String[] labels =  {"팔굽혀펴기", "윗몸일으키기", "3km 달리기"};
        XAxis xAxis = radarChart.getXAxis();
        xAxis.setTextSize(13.5f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        YAxis yAxis = radarChart.getYAxis();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(100f);
        yAxis.setLabelCount(5,true);
        yAxis.setTextSize(11f);
        yAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if(value==100){
                    return  "특급";
                }
                else{
                    return "";
                }
            }
        });
        radarChart.getLegend().setEnabled(false);
        radarChart.getDescription().setEnabled(false);
        radarChart.setData(data);


        return view;
    }

    public void onClickCCA(View view) {
        Intent ccaIntent = new Intent(getActivity(), CameraCaptureActivity.class);
        startActivity(ccaIntent);
    }

    public void onClickPushUpBt(View view) {
        Intent puIntent = new Intent(getActivity(), PushUpMeasureActivity.class);
        startActivity(puIntent);
    }

    public void onClickRunning(View view) {
        Intent runningIntent = new Intent(getActivity(), RunningActivity.class);
        startActivityForResult(runningIntent, 0);
    }


    public void onClickDummy(View view){
        for(int j=1; j<5; j++){
            for(int i=10; i<24; i++){
                RecordInfo recordinfo = new RecordInfo();
                recordinfo.setId(j);
                recordinfo.setPushup(67+i+10*j);
                recordinfo.setRunning(720+i+10*j);
                recordinfo.setSitup(80+i+10*j);

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
