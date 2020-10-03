package kr.co.softcampus.tooksampoom;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.pose.Pose;
import com.google.mlkit.vision.pose.PoseLandmark;

import java.util.List;

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

        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.mipmap.test_image_foreground);
        final Task<Pose> ts =VideoClasifier.AnalizeImage(icon);
        Thread tr = new Thread(new Runnable() {
            @Override
            public void run() {
                ts.addOnSuccessListener(new OnSuccessListener<Pose>() {
                    @Override
                    public void onSuccess(Pose pose) {
                        List<PoseLandmark> a = pose.getAllPoseLandmarks();
                        Log.d("Main", pose.getAllPoseLandmarks().get(0).getPosition().toString());
                    }
                });
            }
        });
        tr.run();

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
}