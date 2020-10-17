package kr.co.softcampus.tooksampoom;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordInfo {
    public int sit_up;
    public int push_up;
    public int running;
    public String date;
    public int id;

    public void setPushup(int push_up){
        this.push_up = push_up;
    }
    public void setSitup(int sit_up){
        this.sit_up = sit_up;
    }
    public void setRunning(int running){
        this.running = running;
    }
    public void setDate(String date){
        this.date =  date;
    }
    public void setId(int id){
        this.id = id;
    }
}

