package kr.co.softcampus.tooksampoom;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static kr.co.softcampus.tooksampoom.MainActivity.developer_mode;

public class SettingFragment extends Fragment {

    Button button5;
    Button button6;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting,container, false);
        button5 = view.findViewById(R.id.button5);
        button6 = view.findViewById(R.id.button6);
        if(developer_mode){
            button5.setVisibility(View.VISIBLE);
            button6.setVisibility(View.VISIBLE);
        }
        else{
            button5.setVisibility(View.GONE);
            button6.setVisibility(View.GONE);
        }
        return view;
    }

    public void onClickCCA(View view) {
        Intent ccaIntent = new Intent(getActivity(), TestDataGeneratorActivity.class);
        startActivity(ccaIntent);
    }

    public void onClickDummy(View view){
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