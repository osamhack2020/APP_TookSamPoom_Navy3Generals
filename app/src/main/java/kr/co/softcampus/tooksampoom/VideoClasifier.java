package kr.co.softcampus.tooksampoom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Size;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.pose.Pose;
import com.google.mlkit.vision.pose.PoseDetection;
import com.google.mlkit.vision.pose.PoseDetector;
import com.google.mlkit.vision.pose.PoseDetectorOptions;
import com.google.mlkit.vision.pose.PoseLandmark;


import java.util.List;
import java.util.concurrent.Executor;

public class VideoClasifier {

    Uri filePath;
    Context context;
    int currentTimer = 0;
    int timeIncrement = 50000;
    MediaMetadataRetriever retriever;

    static PoseDetector poseDetector = PoseDetection.getClient(
            new PoseDetectorOptions.Builder()
                    .setDetectorMode(PoseDetectorOptions.STREAM_MODE)
                    .setPerformanceMode(PoseDetectorOptions.PERFORMANCE_MODE_ACCURATE)
                    .build()
    );

    public VideoClasifier (Uri path, Context context) {
        filePath = path;
        this.context = context;
        currentTimer = 0;

        retriever = new MediaMetadataRetriever();
        retriever.setDataSource(getPath(context, filePath));
    }

    public  String getPath(Context context, Uri uri)  {
        String a = filePath.getPath();
        if (uri.toString().contains("content"))
            return filePath.getPath().split(":")[1];
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(uri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public Bitmap getNextBitmap() {
        try {
            retriever = new MediaMetadataRetriever();
            retriever.setDataSource(filePath.getPath().split(":")[1]);
            Bitmap bt = retriever.getFrameAtTime(currentTimer, MediaMetadataRetriever.OPTION_CLOSEST);
            currentTimer += timeIncrement;
            return bt.copy(Bitmap.Config.ARGB_8888,true);

        } catch (RuntimeException ex) {
            ex.printStackTrace();
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
                ex.printStackTrace();

            }
        }
        return null;
    }


    public static Task<Pose> AnalizeImage(Bitmap bp){
        InputImage image = InputImage.fromBitmap(bp, 0);
        return poseDetector.process(image);
    }



}
