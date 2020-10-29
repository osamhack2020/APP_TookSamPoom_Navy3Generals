package kr.co.softcampus.tooksampoom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import static kr.co.softcampus.tooksampoom.R.id.text_toolbar;

public class MainActivity extends AppCompatActivity {

    static boolean developer_mode = true;
    String[] permissions_list = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};

    Toolbar toolbar;
    TextView textView;
    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager = getSupportFragmentManager();
    MainFragment mainFragment = new MainFragment();
    GraphFragment graphFragment = new GraphFragment();
    RankFragment rankFragment = new RankFragment();
    SettingFragment profileFragment = new SettingFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        textView = (TextView)findViewById(text_toolbar);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,mainFragment).commitAllowingStateLoss();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tab1:{
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,mainFragment).commitAllowingStateLoss();
                        textView.setText("메인");
                        return true;
                    } case R.id.tab2:{
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,graphFragment).commitAllowingStateLoss();
                        textView.setText("히스토리");
                        return true;
                    } case R.id.tab3:{
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,rankFragment).commitAllowingStateLoss();
                        textView.setText("랭킹");
                        return true;
                    } case R.id.tab4: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commitAllowingStateLoss();
                        textView.setText("설정");
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
    public void onClickSitUpBt(View view) {
        Intent siIntent = new Intent(this, SitUpMeasureActivity.class);
        startActivity(siIntent);
    }

    public void onClickCCA(View view) {
        Intent ccaIntent = new Intent(this, TestDataGeneratorActivity.class);
        startActivity(ccaIntent);
    }

    public void onClickPushUpBt(View view) {
        Intent puIntent = new Intent(this, PushUpMeasureActivity.class);
        startActivity(puIntent);
    }

    public void onClickRunning(View view) {
        Intent runningIntent = new Intent(this, RunningActivity.class);
        startActivity(runningIntent);
    }

    public void onClickStart(View view) {
        Intent startIntent = new Intent(this, PracticeSelectorActivity.class);
        startActivityForResult(startIntent, 0);
    }

    public void onClickPracticeSelector(View view) {
        Intent practiceSelectorIntent = new Intent(this, PracticeSelectorActivity.class);
        startActivityForResult(practiceSelectorIntent, 0);
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