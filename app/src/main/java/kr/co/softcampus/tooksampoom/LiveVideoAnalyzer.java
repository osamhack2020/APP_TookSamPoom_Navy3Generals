package kr.co.softcampus.tooksampoom;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Size;
import android.widget.ImageView;

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

import org.tensorflow.lite.Interpreter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.concurrent.Executor;

import kr.co.softcampus.tooksampoom.Utils.TSPdrawTools;

public class LiveVideoAnalyzer {

    static PoseDetector poseDetector = PoseDetection.getClient(
            new PoseDetectorOptions.Builder()
                    .setDetectorMode(PoseDetectorOptions.STREAM_MODE)
                    .setPerformanceMode(PoseDetectorOptions.PERFORMANCE_MODE_FAST)
                    .build()
    );

    public static ImageAnalysis getImageAnalysis(Executor executor, ImageView imageView, Interpreter interpreter, String type) {
        ImageAnalysis imageAnalysis =
                new ImageAnalysis.Builder()
                        .setTargetResolution(new Size(1280, 720))
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build();

        imageAnalysis.setAnalyzer(executor, image -> {
            AnalizeImage(image)
                    .addOnSuccessListener(pose -> {
                        List<PoseLandmark> pl = pose.getAllPoseLandmarks();
                        Bitmap overlay = Bitmap.createBitmap(image.getWidth(), image.getHeight(),Bitmap.Config.ARGB_8888);
                        TSPdrawTools.createBodyOverlay(overlay, pl);

                        if (!pl.isEmpty()) {
                            ByteBuffer input = PushUpMeasureActivity.createInput(pl);
                            ByteBuffer output = ByteBuffer.allocateDirect(java.lang.Float.SIZE * 4 / java.lang.Byte.SIZE).order(ByteOrder.nativeOrder());
                            interpreter.run(input, output);
                            output.rewind();
                            float[] result = new float[4];
                            float max = 0;
                            int maxInd = 0;
                            for (int i = 0; i < 4; i++) {
                                float cur = output.getFloat();
                                if (cur > max) {
                                    max = cur;
                                    maxInd = i;
                                }
                            }
                            TSPdrawTools.createCountOverlay(overlay, type, maxInd);
                        }
                        imageView.setImageBitmap(overlay);
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
