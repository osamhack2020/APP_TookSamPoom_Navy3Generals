package kr.co.softcampus.tooksampoom;

import com.google.mlkit.vision.pose.PoseLandmark;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class DataWriter {

    public static void WriteData(OutputStream fos, List<PoseLandmark> pl, String result) {
        try {
            if (pl.isEmpty())
                return;
            StringBuilder sb = new StringBuilder();
            sb.append(result + "\n");
            for (PoseLandmark p : pl) {
                sb.append("\"" + p.getLandmarkType().name() + "\"," )
                        .append(p.getPosition().x + "," + p.getPosition().y)
                        .append(",").append(p.getInFrameLikelihood());
            }
            sb.append("\n");
            fos.write((sb.toString()).getBytes());
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
