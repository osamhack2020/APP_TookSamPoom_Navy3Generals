package kr.co.softcampus.tooksampoom;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import kr.co.softcampus.tooksampoom.Utils.PracticeSelectorRecycleAdapter;

public class MainActivity extends AppCompatActivity {

    String[] permissions_list = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};

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
        Intent ccaIntent = new Intent(this, CameraCaptureActivity.class);
        startActivity(ccaIntent);
    }
    
    public void onClickPracticeSelector(View view) {
        Intent ptSelector = new Intent(this, PracticeSelectorActivity.class);
        startActivity(ptSelector);
    }

    public void onClickPushUpBt(View view) {
        Intent puIntent = new Intent(this, PushUpMeasureActivity.class);
        startActivity(puIntent);
    }
}