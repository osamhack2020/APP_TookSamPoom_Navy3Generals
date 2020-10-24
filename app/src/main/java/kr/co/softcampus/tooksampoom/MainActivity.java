package kr.co.softcampus.tooksampoom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.mlkit.vision.pose.Pose;
import com.google.mlkit.vision.pose.PoseLandmark;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    String[] permissions_list = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};

    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager = getSupportFragmentManager();
    MainFragment mainFragment = new MainFragment();
    GraphFragment graphFragment = new GraphFragment();
    RankFragment rankFragment = new RankFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,mainFragment).commitAllowingStateLoss();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tab1:{
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,mainFragment).commitAllowingStateLoss();
                        return true;
                    } case R.id.tab2:{
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,graphFragment).commitAllowingStateLoss();
                        return true;
                    } case R.id.tab3:{
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,rankFragment).commitAllowingStateLoss();
                        return true;
                    } case R.id.tab4: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commitAllowingStateLoss();
                        return true;
                    }
                    default: return false;
                }
            }
        });

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
        Intent ccaIntent = new Intent(this, CameraCaptureActivity.class);
        startActivity(ccaIntent);
    }

    public void onClickPushUpBt(View view) {
        Intent puIntent = new Intent(this, PushUpMeasureActivity.class);
        startActivity(puIntent);
    }

    public void onClickRunning(View view) {
        Intent runningIntent = new Intent(this, RunningActivity.class);
        startActivityForResult(runningIntent, 0);
    }

    public void onClickRecord(View view) {
        Intent recordIntent = new Intent(this, GraphActivity.class);
        startActivityForResult(recordIntent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int runningTime = data.getIntExtra("time",0);
        //time_running.setText(Integer.toString(runningTime / 60)+"분 "+Integer.toString(runningTime%60)+"초");
    }

    public void onClickDummy(View view){
        for(int j=1; j<5; j++){
            for(int i=10; i<24; i++){
                RecordInfo recordinfo = new RecordInfo();
                recordinfo.setId(j);
                recordinfo.setPushup(67+i+10*j);
                recordinfo.setRunning(720+i+10*j);
                recordinfo.setSitup(80+i+10*j);

                DBhelper helper = new DBhelper(this);
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