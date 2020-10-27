package kr.co.softcampus.tooksampoom;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.mlkit.vision.pose.PoseLandmark;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import kr.co.softcampus.tooksampoom.Utils.TSPdrawTools;

public class TestDataGeneratorActivity extends AppCompatActivity {

    private static final int CREATE_FILE = 1;
    private static final int PICK_VIDEO = 2;

    ImageView ccImageView;
    ImageView ccaBodyOverlayImageview;
    VideoClasifier vc;
    ListView ccaListView;
    OutputStream tspOutputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_capture);
        ccImageView = findViewById(R.id.camera_cap_image);
        ccaBodyOverlayImageview = findViewById(R.id.cca_body_imageview);
        ccaListView = findViewById(R.id.ccm_list);
        ccaListView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1));

        Intent pickIntent = new Intent(Intent.ACTION_GET_CONTENT);
        pickIntent.setType("*/*");
        createFile();
        startActivityForResult(pickIntent, PICK_VIDEO);
    }

    private void createFile() {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/cvs");
        intent.putExtra(Intent.EXTRA_TITLE, "TSPclassify");

        startActivityForResult(intent, CREATE_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_VIDEO) {
            if (resultCode == RESULT_OK) {
                Uri selectedMediaUri = data.getData();
                vc = new VideoClasifier(selectedMediaUri, this);
                Bitmap bt = vc.getNextBitmap();
                ccImageView.setImageBitmap(bt);
            }
        } else if(requestCode == CREATE_FILE) {
            Uri uri = null;
            if (data != null) {
                uri = data.getData();
                try {
                    tspOutputStream = getContentResolver().openOutputStream(uri, "rw");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void ProcessButton(final String imageResult) {
        final Bitmap bt = vc.getNextBitmap();
        final Context cont = this;
        if (bt == null)
            return;
        runOnUiThread(() -> ccImageView.setImageBitmap(bt));
        Thread tread = new Thread(() -> VideoClasifier.AnalizeImage(bt)
                .addOnSuccessListener(
                        pose -> {
                            if(pose != null) {
                                List<PoseLandmark> lm = pose.getAllPoseLandmarks();
                                final List<String> list = new ArrayList<>();
                                DataWriter.WriteData(tspOutputStream, lm, imageResult);
                                for (PoseLandmark pl : lm) {
                                    list.add(pl.getLandmarkType().name() + " = "
                                            + pl.getPosition().x
                                            + ", " + pl.getPosition().y
                                            + "--" + pl.getInFrameLikelihood());
                                }
                                Bitmap overlay = Bitmap.createBitmap(bt.getWidth(), bt.getHeight(), Bitmap.Config.ARGB_8888);
                                TSPdrawTools.createBodyOverlay(overlay, lm);
                                ccaBodyOverlayImageview.setImageBitmap(overlay);
                                runOnUiThread(() -> {
                                    ArrayAdapter<String> ad = ((ArrayAdapter<String>)ccaListView.getAdapter());
                                    if(!ad.isEmpty())
                                        ad.clear();
                                    ad.addAll(list);
                                    ad.notifyDataSetChanged();
                                });
                            }
                        })
                .addOnFailureListener(
                        e -> {
                            Log.d("CCA", e.toString());
                            Log.d("CCA", e.getMessage());
                            runOnUiThread(() -> ccaListView.setAdapter(new ArrayAdapter<String>(cont,
                                    android.R.layout.simple_list_item_1)));
                        }));
        tread.run();
    }

    public void OnClickStand(View view) {
        ProcessButton("stand");
    }

    public void OnClickDown(View view) {
        ProcessButton("down");
    }

    public void OnClickMove(View view) {
        ProcessButton("move");
    }

    public void OnClickFail(View view) {
        ProcessButton("fail");
    }

}