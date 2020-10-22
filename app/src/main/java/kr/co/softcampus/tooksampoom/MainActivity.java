package kr.co.softcampus.tooksampoom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    String[] permissions_list = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();

    }

    public void checkPermission() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
        for(String permission : permissions_list) {
            int chk = checkCallingOrSelfPermission(permission);
            if(chk == PackageManager.PERMISSION_DENIED) {
                requestPermissions(permissions_list, 0);
                break;
            }
        }
    }

    public void onClickCCA(View view) {
        Intent ccaIntent = new Intent(this, TestDataGeneratorActivity.class);
        startActivity(ccaIntent);
    }

    public void onClickPushUpBt(View view) {
        Intent puIntent = new Intent(this, PushUpMeasureActivity.class);
        startActivity(puIntent);
    }

    public void onClickSitUpBt(View view) {
        Intent siIntent = new Intent(this, SitUpMeasureActivity.class);
        startActivity(siIntent);
    }

    public void onClickRunning(View view) {
        Intent runningIntent = new Intent(this, RunningActivity.class);
        startActivityForResult(runningIntent, 0);
    }

    public void onClickRecord(View view) {
        Intent recordIntent = new Intent(this, GraphActivity.class);
        startActivityForResult(recordIntent, 0);
    }

    public void onClickPracticeSelector(View view) {
        Intent practiceSelectorIntent = new Intent(this, PracticeSelectorActivity.class);
        startActivityForResult(practiceSelectorIntent, 0);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int runningTime = data.getIntExtra("time",0);
        //time_running.setText(Integer.toString(runningTime / 60)+"분 "+Integer.toString(runningTime%60)+"초");
    }

    public void onClickDummy(View view){
        init();
    }

    public void init(){
        for(int i=10; i<24; i++){
            RecordInfo recordinfo = new RecordInfo();
            recordinfo.setId(1);
            recordinfo.setPushup(67+i);
            recordinfo.setRunning(720+i);
            recordinfo.setSitup(80+i);
            //Date date = new Date(System.currentTimeMillis());
            //SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd");
            //String formatDate = sdfNow.format(date);
            String formatDate = "2020-08-"+i;
            recordinfo.setDate(formatDate);
            DBhelper.setPushUpRecord(this, Integer.parseInt(recordinfo.id),  Integer.parseInt(recordinfo.push_up));
            DBhelper.setSitUpRecord(this,  Integer.parseInt(recordinfo.id),  Integer.parseInt(recordinfo.sit_up));
            DBhelper.setRunningRecord(this,  Integer.parseInt(recordinfo.id),  Integer.parseInt(recordinfo.running));
        }
    }
}