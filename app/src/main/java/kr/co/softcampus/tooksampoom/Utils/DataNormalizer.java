package kr.co.softcampus.tooksampoom.Utils;

import com.google.mlkit.vision.pose.PoseLandmark;

import java.util.ArrayList;
import java.util.List;

public class DataNormalizer {

    private static int _leftShoulderInd = 11;
    private static int _leftHipInd = 23;

    public static List<Float> NormalizeWithAxisOnBody(List<PoseLandmark> plList) {
        float x1 = (plList.get(_leftShoulderInd).getPosition().x + plList.get(_leftShoulderInd + 1).getPosition().x) / 2;
        float y1 = (plList.get(_leftShoulderInd + 1).getPosition().y + plList.get(_leftShoulderInd + 1).getPosition().y) / 2;
        float x2 = (plList.get(_leftHipInd).getPosition().x + plList.get(_leftHipInd).getPosition().x) / 2;
        float y2 = (plList.get(_leftHipInd + 1).getPosition().x + plList.get(_leftHipInd + 1).getPosition().x) / 2;
        double radian = Math.atan(Double.parseDouble(new Float((y2 - y1) / (x2 - x1)).toString()));

        float xmax = 0;
        float ymax = 0;
        float xmin = Integer.MAX_VALUE;
        float ymin = Integer.MAX_VALUE;
        List<Float> xList = new ArrayList();
        List<Float> yList = new ArrayList<>();
        List<Float> probList = new ArrayList<>();

        for (PoseLandmark pl : plList) {
            float xnew = pl.getPosition().x * (float) Math.cos(radian) + pl.getPosition().y * (float) Math.sin(radian);
            float ynew = -1 * pl.getPosition().x *(float) Math.sin(radian) + pl.getPosition().y * (float) Math.cos(radian);
            xmax = Math.max(xmax, xnew);
            ymax = Math.max(ymax, ynew);
            xmin = Math.min(xmin, xnew);
            ymin = Math.min(ymin, ynew);
            xList.add(xnew);
            yList.add(ynew);
            probList.add(pl.getInFrameLikelihood());
        }

        List<Float> ans = new ArrayList<>();

        for (float x : xList)
            ans.add((x - xmin) / (xmax - xmin));
        for (float y : yList)
            ans.add((y - ymin) / (ymax - ymin));
        for (float p : probList)
            ans.add(p);
        return ans;
    }
}
