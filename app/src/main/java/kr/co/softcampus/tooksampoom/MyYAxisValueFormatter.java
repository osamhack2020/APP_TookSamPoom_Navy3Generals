package kr.co.softcampus.tooksampoom;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

public class MyYAxisValueFormatter implements IAxisValueFormatter {

    public MyYAxisValueFormatter(){
    }


    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        return -1*(int)value / 60 + "분 " + -1*(int)value % 60 + "초";
    }

}
