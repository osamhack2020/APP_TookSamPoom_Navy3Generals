package kr.co.softcampus.tooksampoom;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;



import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.pose.Pose;
import com.google.mlkit.vision.pose.PoseDetection;
import com.google.mlkit.vision.pose.PoseDetector;
import com.google.mlkit.vision.pose.PoseDetectorOptions;
import com.google.mlkit.vision.pose.PoseLandmark;


import java.io.Console;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;


public class VideoClasifier {

    Uri filePath;
    Context context;
    int currentTimer = 0;
    MediaMetadataRetriever retriever;

    public VideoClasifier (Uri path, Context context) {
        filePath = path;
        this.context = context;
        currentTimer = 0;

        retriever = new MediaMetadataRetriever();
        retriever.setDataSource(filePath.getPath().split(":")[1]);
    }

    public  String getPath(Context context, Uri uri)  {
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
            currentTimer += 1000000;
            return bt;

        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
            }
        }
        return null;
    }


    public static Task<Pose> AnalizeImage(Bitmap bp){
        PoseDetectorOptions options =
                new PoseDetectorOptions.Builder()
                        .setDetectorMode(PoseDetectorOptions.SINGLE_IMAGE_MODE)
                        .setPerformanceMode(PoseDetectorOptions.PERFORMANCE_MODE_ACCURATE)
                        .build();
        PoseDetector poseDetector = PoseDetection.getClient(options);
        InputImage image = InputImage.fromBitmap(bp, 0);
        Task<Pose> result = poseDetector.process(image);
        return result;
    }
}
