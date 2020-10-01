package kr.co.softcampus.tooksampoom;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.pose.Pose;
import com.google.mlkit.vision.pose.PoseDetection;
import com.google.mlkit.vision.pose.PoseDetector;
import com.google.mlkit.vision.pose.PoseDetectorOptions;
import com.google.mlkit.vision.pose.PoseLandmark;

import org.bytedeco.javacv.AndroidFrameConverter;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;

import java.io.Console;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;


public class VideoClasifier {

    Uri filePath;
    Context context;
    private FFmpegFrameGrabber grabber;
    private AndroidFrameConverter converterToBitmap;

    public VideoClasifier (Uri path, Context context) {
        filePath = path;
        this.context = context;

        try {
            InputStream inputStream = context.getContentResolver().openInputStream(filePath);
            grabber = new FFmpegFrameGrabber(inputStream);
            converterToBitmap = new AndroidFrameConverter();
            grabber.setFormat(grabber.getFormat());
            grabber.start();


        } catch (FileNotFoundException | FrameGrabber.Exception e) {
            Log.e("VideoClasifier", e.toString());
            e.printStackTrace();
        }
    }

    public Bitmap getNextBitmap() {
        try {
            Frame nthFrame = grabber.grabImage();
            return converterToBitmap.convert((nthFrame));
        } catch (FrameGrabber.Exception e) {
            Log.e("VideoClasifier", e.toString());
            e.printStackTrace();
        }
        return null;
    }


    public static List<PoseLandmark> AnalizeImage(Bitmap bp){
        PoseDetectorOptions options =
                new PoseDetectorOptions.Builder()
                        .setDetectorMode(PoseDetectorOptions.SINGLE_IMAGE_MODE)
                        .setPerformanceMode(PoseDetectorOptions.PERFORMANCE_MODE_ACCURATE)
                        .build();
        PoseDetector poseDetector = PoseDetection.getClient(options);
        InputImage image = InputImage.fromBitmap(bp, 0);
        Task<Pose> result =
                poseDetector.process(image);
        if (result.isSuccessful())
            return result.getResult().getAllPoseLandmarks();
        return null;
    }
}
