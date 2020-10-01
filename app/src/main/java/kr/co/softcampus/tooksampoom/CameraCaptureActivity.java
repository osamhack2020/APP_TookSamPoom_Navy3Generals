package kr.co.softcampus.tooksampoom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.pose.Pose;
import com.google.mlkit.vision.pose.PoseDetection;
import com.google.mlkit.vision.pose.PoseDetector;
import com.google.mlkit.vision.pose.PoseDetectorOptions;
import com.google.mlkit.vision.pose.PoseLandmark;

import java.util.ArrayList;
import java.util.List;

public class CameraCaptureActivity extends AppCompatActivity {

    ImageView ccImageview;
    VideoClasifier vc;
    ListView ccaListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_capture);
        ccImageview = findViewById(R.id.camera_cap_image);
        ccaListView = findViewById(R.id.ccm_list);

        Intent pickIntent = new Intent(Intent.ACTION_GET_CONTENT);
        pickIntent.setType("*/*");
        startActivityForResult(pickIntent, 10);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri selectedMediaUri = data.getData();
            if (selectedMediaUri.toString().contains("image")) {
                //handle image
            } else  {
                vc = new VideoClasifier(selectedMediaUri, this);
                Bitmap bt = vc.getNextBitmap();
                ccImageview.setImageBitmap(bt);
            }
        }
    }

    public void OnClickStand(View view) {
        final Bitmap bt = vc.getNextBitmap();
        final Context cont = this;
        if (bt == null)
            return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ccImageview.setImageBitmap(bt);
            }
        });

        Thread tread = new Thread(new Runnable() {
            @Override
            public void run() {
                VideoClasifier.AnalizeImage(bt)
                        .addOnSuccessListener(
                        new OnSuccessListener<Pose>() {
                            @Override
                            public void onSuccess(Pose pose) {
                                if(pose != null) {
                                    List<PoseLandmark> lm = pose.getAllPoseLandmarks();
                                    Log.d("CCA", lm.get(0).toString());
                                    final List<String> list = new ArrayList<>();
                                    for (PoseLandmark pl : lm) {
                                        list.add(pl.getLandmarkType().name() + " = "
                                                + pl.getPosition().x
                                         + ", " + pl.getPosition().y);
                                    }
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            ArrayAdapter<String> ad = new ArrayAdapter<String>(cont, android.R.layout.simple_list_item_1, list);
                                            ccaListView.setAdapter(ad);
                                        }
                                    });

                                }
                            }
                        })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("CCA", e.toString());
                                        Log.d("CCA", e.getMessage());
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                ccaListView.setAdapter(new ArrayAdapter<String>(cont,
                                                        android.R.layout.simple_list_item_1));
                                            }
                                        });
                                    }
                                });
            }
        });
        tread.run();

    }

}