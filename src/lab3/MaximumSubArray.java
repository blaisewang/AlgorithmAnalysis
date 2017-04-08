package lab3;

import java.util.ArrayList;

/**
 * AlgorithmAnalysis
 * Created by blaisewang on 2017/3/29.
 */
public class MaximumSubArray {
    public static ArrayList<Integer> getMaximumSubArray(int[] array) {
        int maximumSum = 0, currentSum = 0;
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int anInteger : array) {
            currentSum += anInteger;
            if (currentSum > maximumSum) {
                maximumSum = currentSum;
            }

            if (currentSum < 0) {
                currentSum = 0;
            }
        }

        currentSum = 0;
        for (int anInteger : array) {
            currentSum += anInteger;
            arrayList.add(anInteger);
            if (currentSum == maximumSum) {
                return arrayList;
            }

            if (currentSum < 0) {
                currentSum = 0;
                arrayList.clear();
            }
        }

        return arrayList;
    }
}
