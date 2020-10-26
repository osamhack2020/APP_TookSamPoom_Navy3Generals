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
    Button button7;
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
        button7 = (Button) view.findViewById(R.id.button7);

        radarChart = (RadarChart) view.findViewById((R.id.chart));


        ArrayList<RadarEntry> dataValue = new ArrayList<>();
        dataValue.add(new RadarEntry(formatPushUp(120)));
        dataValue.add(new RadarEntry(formatSitUp(80)));
        dataValue.add(new RadarEntry(formatRunning(1300)));

        RadarDataSet dataSet = new RadarDataSet(dataValue, "data");
        dataSet.setColor(Color.BLUE);
        dataSet.setColor(R.color.colorPrimary);
        dataSet.setDrawFilled(true);
        dataSet.setValueTextSize(10f);
        RadarData data = new RadarData();
        String[] values = {120+"개", 80+"개", 1300/60+"분 "+1300%60+"초"};
        dataSet.setValueFormatter(new MyValueFormatter(values));
        data.addDataSet(dataSet);

        String[] labels =  {"팔굽혀펴기", "윗몸일으키기", "3km 달리기"};
        XAxis xAxis = radarChart.getXAxis();
        xAxis.setTextSize(11f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        YAxis yAxis = radarChart.getYAxis();
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum(5);
        yAxis.setLabelCount(6,true);
        yAxis.setTextSize(9f);
        yAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if(value==0){
                    return "";
                }
                if(value==1){
                    return "미달";
                }
                if(value==2){
                    return "3급";
                }
                if(value==3){
                    return "2급";
                }
                if(value==4){
                    return "1급";
                }
                if(value==5){
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

    public float formatPushUp(int value){
        if(value>100){
            return 5;
        }
        if(value<=47){
            return (float)value/47;
        }
        else if(value>=48 && value<=55){
            return 1+(float)(value-48)/(55-48);
        }
        else if(value>=56 && value<=63){
            return 2+(float)(value-56)/(63-56);
        }
        else if(value>=64 && value<=71){
            return 3+(float)(value-64)/(71-64);
        }
        else{
            return 4+(float)(value-72)/(100-72);
        }
    }

    public float formatSitUp(int value){
        if(value>100){
            return 5;
        }
        if(value<=61){
            return (float)value/61;
        }
        else if(value>=62 && value<=69){
            return 1+(float)(value-62)/(69-62);
        }
        else if(value>=70 && value<=77){
            return 2+(float)(value-70)/(77-70);
        }
        else if(value>=78 && value<=85){
            return 3+(float)(value-78)/(85-78);
        }
        else{
            return 4+(float)(value-86)/(100-86);
        }
    }

    public float formatRunning(int value){
        int mValue = value*-1;
        if(mValue>-600){
            return 5;
        }
        if(mValue<-1200){
            return 0;
        }
        if(mValue<=-937){
            return (float)mValue-(-1200)/(-937-(-1200));
        }
        else if(mValue>=-936 && mValue<=-875){
            return 1+(float)(mValue-(-936))/(-875-(-936));
        }
        else if(mValue>=-874 && mValue<=-813){
            return 2+(float)(mValue-(-874))/(-813-(-874));
        }
        else if(mValue>=-812 && mValue<=-751){
            return 3+(float)(mValue-(-812))/(-751-(-812));
        }
        else{
            return 4+(float)(mValue-(-750))/(-600-(-750));
        }
    }

    public void onClickCCA(View view) {
        Intent ccaIntent = new Intent(getActivity(), CameraCaptureActivity.class);
        startActivity(ccaIntent);
    }

    public void onClickPushUpBt(View view) {
        Intent puIntent = new Intent(getActivity(), PushUpMeasureActivity.class);
        startActivity(puIntent);
    }
    public void onClickSitUpBt(View view) {
        Intent siIntent = new Intent(getActivity(), SitUpMeasureActivity.class);
        startActivity(siIntent);
    }

    public void onClickRunning(View view) {
        Intent runningIntent = new Intent(getActivity(), RunningActivity.class);
        startActivityForResult(runningIntent, 0);
    }

    public void onClickStart(View view) {
        Intent startIntent = new Intent(getActivity(), PracticeSelectorActivity.class);
        startActivityForResult(startIntent, 0);
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
