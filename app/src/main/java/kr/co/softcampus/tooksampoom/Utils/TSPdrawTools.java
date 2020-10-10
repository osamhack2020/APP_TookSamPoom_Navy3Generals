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

    static Paint bodyPaint = null;

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
}
