package kr.co.softcampus.tooksampoom;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.pose.PoseLandmark;

import org.tensorflow.lite.Interpreter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import kr.co.softcampus.tooksampoom.Utils.ActivityMode;
import kr.co.softcampus.tooksampoom.Utils.DataNormalizer;
import kr.co.softcampus.tooksampoom.Utils.LimitedQueue;


public class PushUpMeasureActivity extends AppCompatActivity {

    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private static boolean _isStarted = false;
    private static final String _pushUpModelName = "push_up_axisOnWristToAnkle.tflite";
    protected static int _countDown = 120;
    PreviewView previewView;
    TextView push_up_timer_textView;
    TextView textView2;
    TextView text_result;
    Button pushUpStartButton;
    ImageView pushUpBodyImageView;
    Interpreter pushUpInterpreter;
    public static String[] pushUpStatus = new String[]{"stand", "move", "down", "fail"};
    public static boolean DownHit;
    public static int Count;
    public static List<Integer> LatestPostures = new LimitedQueue<>(6);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_up_measure);
        Count = 0;
        DownHit = false;
        previewView = findViewById(R.id.previewView);
        pushUpBodyImageView = findViewById(R.id.sit_up_body);
        pushUpStartButton = findViewById(R.id.sit_up_start_button);
        push_up_timer_textView = findViewById(R.id.push_up_timer);
        textView2 = findViewById(R.id.textView2);
        text_result = findViewById(R.id.text_result);
        text_result.setVisibility(View.GONE);
        pushUpBodyImageView = findViewById(R.id.push_up_body);
        pushUpStartButton = findViewById(R.id.push_up_start_button);
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindPreview(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                // No errors need to be handled for this Future.
                // This should never be reached.
            }
        }, ContextCompat.getMainExecutor(this));
        setPushUpInterpreter();
    }

    void setPushUpInterpreter() {
        try {
            InputStream inputStream = getAssets().open(_pushUpModelName);
            byte[] model = new byte[inputStream.available()];
            inputStream.read(model);
            ByteBuffer buffer = ByteBuffer.allocateDirect(model.length)
                    .order(ByteOrder.nativeOrder());
            buffer.put(model);
            pushUpInterpreter = new Interpreter(buffer);
        } catch (IOException e) {
            // File not found?
        }
    }

    void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder().build();
        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();
        preview.setSurfaceProvider(previewView.createSurfaceProvider());
        ImageAnalysis analysis = LiveVideoAnalyzer.getImageAnalysis(Executors.newSingleThreadExecutor(), pushUpBodyImageView,
                push_up_timer_textView, textView2, pushUpInterpreter, ActivityMode.PushUp);
        cameraProvider.bindToLifecycle(this, cameraSelector, analysis, preview);
    }

    /**
     * PushUp 모델을 위한 인풋 버퍼 생성
     * @param landmarks
     * @return
     */
    public static ByteBuffer createInput(List<PoseLandmark> landmarks) {
        ByteBuffer input = ByteBuffer.allocateDirect(99 * java.lang.Float.SIZE / java.lang.Byte.SIZE).order(ByteOrder.nativeOrder());
        List<Float> inputList = DataNormalizer.NormalizeWithAxisOnHandToFeet(landmarks);
        for (Float f : inputList)
            input.putFloat(f);
        return input;
    }

    public void onClickStartButton(View view) {
        _countDown = 120;
        pushUpStartButton.setVisibility(View.INVISIBLE);
        _isStarted = true;
        Context _ct = this;
        new CountDownTimer(120500, 1000){
            public void onTick(long millisUntilFinished){
                _countDown --;
            }
            public  void onFinish(){
                pushUpStartButton.setVisibility(View.VISIBLE);
                pushUpStartButton.setText("기록저장하기");
                text_result.setText(resultCalculator(Count));
                text_result.setVisibility(View.VISIBLE);
                DBhelper.setPushUpRecord(_ct, 1,Count);
                pushUpStartButton.setOnClickListener(null);
                pushUpStartButton.setOnClickListener(v -> {
                    finish();
                });
                _isStarted = false;
            }
        }.start();
    }

    public static int getCurrentPosture() {
        int sum = 0;
        for (int i : LatestPostures)
            sum += i;
        return Math.round((float) sum / LatestPostures.size());
    }

    public static void updateCounter() {
        int currentPosture = getCurrentPosture();
        if (currentPosture == 0) {
            if (DownHit && _isStarted)
                Count ++;
            DownHit = false;
        }
        if (currentPosture == 2)
            DownHit = true;
    }

    public static String resultCalculator(int count){
        if(count<48){
            return "미달";
        }
        if(count>=48&&count<=55){
            return "3급";
        }
        if(count>=56&&count<=63){
            return "2급";
        }
        if(count>=64&&count<=71){
            return "1급";
        }
        else{
            return "특급";
        }
    }

}