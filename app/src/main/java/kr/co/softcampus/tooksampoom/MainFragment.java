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
import android.widget.TextView;

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
    RadarChart radarChart;
    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main,container, false);
        radarChart = (RadarChart) view.findViewById((R.id.chart));
        textView = (TextView) view.findViewById(R.id.text_name);

        textView.setText("일병 정민석님");

        ArrayList<RadarEntry> dataValue = new ArrayList<>();
        dataValue.add(new RadarEntry(formatPushUp(57)));
        dataValue.add(new RadarEntry(formatSitUp(80)));
        dataValue.add(new RadarEntry(formatRunning(720)));
        RadarDataSet dataSet = new RadarDataSet(dataValue, "data");
        dataSet.setColor(Color.BLACK);
        dataSet.setDrawFilled(true);
        dataSet.setFillColor(Color.rgb(40,104,176));
        RadarData data = new RadarData();
        String[] values = {57+"개", 80+"개", 720/60+"분 "+720%60+"초"};
        dataSet.setValueFormatter(new MyValueFormatter(values));
        dataSet.setValueTextSize(11f);
        data.addDataSet(dataSet);

        String[] labels =  {"팔굽혀펴기", "윗몸일으키기", "3km 달리기"};
        XAxis xAxis = radarChart.getXAxis();
        xAxis.setTextSize(13f);
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

    public void onClickStart(View view) {
        Intent startIntent = new Intent(getActivity(), PracticeSelectorActivity.class);
        startActivityForResult(startIntent, 0);
    }
}
