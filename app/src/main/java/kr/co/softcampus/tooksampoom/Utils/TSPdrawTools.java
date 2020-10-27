package kr.co.softcampus.tooksampoom.Utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Pair;

import com.google.mlkit.vision.pose.PoseLandmark;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TSPdrawTools {

    static Pair[] bodyPair = new Pair[]{
            Pair.create(PoseLandmark.Type.LEFT_WRIST, PoseLandmark.Type.LEFT_ELBOW),
            Pair.create(PoseLandmark.Type.LEFT_ELBOW , PoseLandmark.Type.LEFT_SHOULDER),
            Pair.create(PoseLandmark.Type.LEFT_SHOULDER , PoseLandmark.Type.RIGHT_SHOULDER),
            Pair.create(PoseLandmark.Type.RIGHT_SHOULDER , PoseLandmark.Type.RIGHT_ELBOW),
            Pair.create(PoseLandmark.Type.RIGHT_ELBOW , PoseLandmark.Type.RIGHT_WRIST),
            Pair.create(PoseLandmark.Type.LEFT_SHOULDER , PoseLandmark.Type.LEFT_HIP),
            Pair.create(PoseLandmark.Type.LEFT_HIP , PoseLandmark.Type.RIGHT_HIP),
            Pair.create(PoseLandmark.Type.RIGHT_HIP , PoseLandmark.Type.RIGHT_SHOULDER),
            Pair.create(PoseLandmark.Type.LEFT_HIP , PoseLandmark.Type.LEFT_KNEE),
            Pair.create(PoseLandmark.Type.LEFT_KNEE , PoseLandmark.Type.LEFT_ANKLE),
            Pair.create(PoseLandmark.Type.RIGHT_HIP , PoseLandmark.Type.RIGHT_KNEE),
            Pair.create(PoseLandmark.Type.RIGHT_KNEE , PoseLandmark.Type.RIGHT_ANKLE)
    };

    private static Paint bodyPaint = null;
    private static Paint _countPaint = null;
    private static Paint _timerPaint = null;

    public static Paint getBodyPaint() {
        if (bodyPaint != null)
            return bodyPaint;
        bodyPaint = new Paint();
        bodyPaint.setAntiAlias(true);
        bodyPaint.setStyle(Paint.Style.STROKE);
        bodyPaint.setColor(Color.RED);
        bodyPaint.setStrokeWidth(10f);
        return bodyPaint;
    }

    public static Paint getCountPaint() {
        if (_countPaint != null)
            return _countPaint;
        _countPaint = new Paint();
        _countPaint.setAntiAlias(true);
        _countPaint.setStyle(Paint.Style.FILL);
        _countPaint.setColor(Color.BLUE);
        _countPaint.setStrokeWidth(3f);
        _countPaint.setTextSize(40f);
        return _countPaint;
    }

    public static Paint getTimerPaint() {
        if (_timerPaint != null)
            return _timerPaint;
        _timerPaint = new Paint();
        _timerPaint.setAntiAlias(true);
        _timerPaint.setStyle(Paint.Style.FILL);
        _timerPaint.setColor(Color.WHITE);
        _timerPaint.setStrokeWidth(3f);
        _timerPaint.setTextSize(100f);
        return _timerPaint;
    }

    public static void createBodyOverlay(Bitmap overlay, List<PoseLandmark> landmarks) {
        Paint paint = getBodyPaint();
        Canvas canvas = new Canvas(overlay);
        Map<PoseLandmark.Type, PoseLandmark> landmakrMap = new HashMap();
        for (PoseLandmark plm : landmarks) {
            landmakrMap.put(plm.getLandmarkType(), plm);
        }
        for (Pair pair : bodyPair) {
            PoseLandmark start = landmakrMap.get(pair.first);
            PoseLandmark end = landmakrMap.get(pair.second);
            if (start == null || end == null)
                continue;
            canvas.drawLine(start.getPosition().x, start.getPosition().y,
                    end.getPosition().x, end.getPosition().y, paint);
        }
    }

    public static void createCountOverlay(Bitmap overlay, String type, int count, int time, int currentPose) {
        Paint countPaint = getCountPaint();
        Canvas canvas = new Canvas(overlay);
        canvas.drawText(type + ": " + count + " : " + currentPose,overlay.getWidth() * 0.45f,
                overlay.getHeight() * 0.9f, countPaint);
        Paint timerPaint = getTimerPaint();
        if (time == 0) {
            canvas.drawText("Finished!", overlay.getWidth() * 0.45f, overlay.getHeight() * 0.1f,
                    timerPaint);
        } else {
            canvas.drawText(Integer.toString(time), overlay.getWidth() * 0.45f, overlay.getHeight() * 0.1f,
                    timerPaint);
        }
    }
}
