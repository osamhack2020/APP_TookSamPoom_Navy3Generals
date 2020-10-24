package kr.co.softcampus.tooksampoom.Utils;

import com.google.mlkit.vision.pose.PoseLandmark;

import java.util.ArrayList;
import java.util.List;

public class DataNormalizer {

    private static final int _leftShoulderInd = 11;
    private static final int _leftHipInd = 23;

    public static List<Float> NormalizeWithAxisOnBody(List<PoseLandmark> plList) {
        float x1 = (plList.get(_leftShoulderInd).getPosition().x + plList.get(_leftShoulderInd + 1).getPosition().x) / 2;
        float y1 = (plList.get(_leftShoulderInd + 1).getPosition().y + plList.get(_leftShoulderInd + 1).getPosition().y) / 2;
        float x2 = (plList.get(_leftHipInd).getPosition().x + plList.get(_leftHipInd).getPosition().x) / 2;
        float y2 = (plList.get(_leftHipInd + 1).getPosition().x + plList.get(_leftHipInd + 1).getPosition().x) / 2;
        double radian = Math.atan(Double.parseDouble(Float.valueOf((y2 - y1) / (x2 - x1)).toString()));

        float xMax = 0;
        float yMax = 0;
        float xMin = Integer.MAX_VALUE;
        float yMin = Integer.MAX_VALUE;
        List<Float> xList = new ArrayList<>();
        List<Float> yList = new ArrayList<>();
        List<Float> probList = new ArrayList<>();

        for (PoseLandmark pl : plList) {
            float xnew = pl.getPosition().x * (float) Math.cos(radian) + pl.getPosition().y * (float) Math.sin(radian);
            float ynew = -1 * pl.getPosition().x *(float) Math.sin(radian) + pl.getPosition().y * (float) Math.cos(radian);
            xMax = Math.max(xMax, xnew);
            yMax = Math.max(yMax, ynew);
            xMin = Math.min(xMin, xnew);
            yMin = Math.min(yMin, ynew);
            xList.add(xnew);
            yList.add(ynew);
            probList.add(pl.getInFrameLikelihood());
        }

        List<Float> ans = new ArrayList<>();

        for (float x : xList)
            ans.add((x - xMin) / (xMax - xMin));
        for (float y : yList)
            ans.add((y - yMin) / (yMax - yMin));
        ans.addAll(probList);
        return ans;
    }
}
