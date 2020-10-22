package kr.co.softcampus.tooksampoom;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyXAxisValueFormatter implements IAxisValueFormatter {

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        Date d = new Date((long) value);
        SimpleDateFormat date = new SimpleDateFormat("MM-dd");
        String Date = date.format(d);
        return Date;

    }

}
