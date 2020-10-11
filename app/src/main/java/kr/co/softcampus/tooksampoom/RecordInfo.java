package kr.co.softcampus.tooksampoom;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordInfo {
    public String sit_up;
    public String push_up;
    public String running;
    public String date;

    public void setPushup(int push_up){
        this.push_up = Integer.toString(push_up);
    }
    public void setSitup(int sit_up){
        this.sit_up = Integer.toString(sit_up);
    }
    public void setRunning(int running){
        this.running = Integer.toString(running);
    }
    public void setDate(String date){
        this.date =  date;
    }
}

