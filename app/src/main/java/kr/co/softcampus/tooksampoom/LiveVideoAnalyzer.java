package kr.co.softcampus.tooksampoom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.media.Image;
import android.util.Size;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;

import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.pose.Pose;
import com.google.mlkit.vision.pose.PoseDetection;
import com.google.mlkit.vision.pose.PoseDetector;
import com.google.mlkit.vision.pose.PoseDetectorOptions;
import com.google.mlkit.vision.pose.PoseLandmark;

import org.tensorflow.lite.Interpreter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.concurrent.Executor;

import kr.co.softcampus.tooksampoom.Utils.ActivityMode;
import kr.co.softcampus.tooksampoom.Utils.TSPdrawTools;

import static kr.co.softcampus.tooksampoom.MainActivity.developer_mode;

public class LiveVideoAnalyzer {

    static PoseDetector poseDetector = PoseDetection.getClient(
            new PoseDetectorOptions.Builder()
                    .setDetectorMode(PoseDetectorOptions.STREAM_MODE)
                    .setPerformanceMode(PoseDetectorOptions.PERFORMANCE_MODE_FAST)
                    .build()
    );
    public static String timeFormatter(int time){
        int mSec = time%60;
        if(mSec>=10){
            return "0"+time/60+":"+mSec;
        }
        else{
            return "0"+time/60+":0"+mSec;
        }
    }

    public static ImageAnalysis getImageAnalysis(Executor executor, TextView textView1, TextView textView2,
                                                 Interpreter interpreter, ActivityMode am) {
        ImageAnalysis imageAnalysis =
                new ImageAnalysis.Builder()
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build();

        imageAnalysis.setAnalyzer(executor, image -> {
            AnalizeImage(image)
                    .addOnSuccessListener(pose -> {
                        List<PoseLandmark> pl = pose.getAllPoseLandmarks();
                        Bitmap overlay = Bitmap.createBitmap(image.getWidth(), image.getHeight(), Bitmap.Config.ARGB_8888);
                        TSPdrawTools.createBodyOverlay(overlay, pl);

                        int maxInd = 0;
                        if (!pl.isEmpty()) {
                            ByteBuffer input = PushUpMeasureActivity.createInput(pl);
                            ByteBuffer output = ByteBuffer.allocateDirect(java.lang.Float.SIZE * 4 / java.lang.Byte.SIZE).order(ByteOrder.nativeOrder());
                            interpreter.run(input, output);
                            output.rewind();
                            float max = 0;
                            for (int i = 0; i < 4; i++) {
                                float cur = output.getFloat();
                                if (cur > max) {
                                    max = cur;
                                    maxInd = i;
                                }
                            }
                            PushUpMeasureActivity.LatestPostures.add(maxInd);
                        }
                        int timer = 0;
                        int count = 0;
                        if (am == ActivityMode.PushUp) {
                            timer = PushUpMeasureActivity._countDown;
                            PushUpMeasureActivity.updateCounter();
                            count = PushUpMeasureActivity.Count;
                        }
                        else{
                            timer = SitUpMeasureActivity._countDown;
                            SitUpMeasureActivity.updateCounter();
                            count = SitUpMeasureActivity.Count;
                        }
                        //TSPdrawTools.createCountOverlay(overlay, am.name(), count, timer, maxInd);
                        //imageView.setImageBitmap(overlay);
                        if(timer == 0){
                            textView1.setText("Finished!");
                        }
                        else {
                            textView1.setText(timeFormatter(timer));
                        }
                        if(developer_mode){
                            textView2.setText("횟수: "+ count +": "+ maxInd);
                        }
                        else{
                            textView2.setText("횟수: "+ count);
                        }
                        image.close();
                    });
        });
        return imageAnalysis;
    }

    public static Task<Pose> AnalizeImage(ImageProxy imageProxy) {
        @SuppressLint("UnsafeExperimentalUsageError")
        Image mediaImage = imageProxy.getImage();
        if (mediaImage != null) {
            InputImage image =
                    InputImage.fromMediaImage(mediaImage, imageProxy.getImageInfo().getRotationDegrees());
            return poseDetector.process(image);
        }
        return null;
    }

}
