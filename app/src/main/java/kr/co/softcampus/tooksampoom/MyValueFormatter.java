package kr.co.softcampus.tooksampoom;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class MyValueFormatter implements IValueFormatter {


    private String[] mValues;
    private int i=-1;
    public MyValueFormatter(String[] values){
        this.mValues = values;
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        i++;
        if(i%3==0){
            return mValues[0];
        }
        if(i%3==1){
            return mValues[1];
        }
        if(i%3==2){
            return mValues[2];
        }
        return "";
    }
}
