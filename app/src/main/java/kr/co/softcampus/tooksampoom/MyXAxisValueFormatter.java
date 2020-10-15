package kr.co.softcampus.tooksampoom;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyXAxisValueFormatter implements IAxisValueFormatter {
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        Date date = new Date((long)value);
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        return sdf.format(date);
    }
}