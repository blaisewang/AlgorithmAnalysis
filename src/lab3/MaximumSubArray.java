package lab3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * AlgorithmAnalysis
 * Created by blaisewang on 2017/3/29.
 */
public class MaximumSubArray {
    private static ArrayList<Integer> getMaximumSubArray(int[] array) {
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

    public static void main(String[] args) {
        int arraySize = new Scanner(System.in).nextInt();
        int[] array = new int[0];
        try {
            array = SecureRandom.getInstanceStrong().ints(arraySize).toArray();
            Files.write(Paths.get("input.txt"), Arrays.toString(array).getBytes());
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }

        ArrayList<Integer> arrayList = getMaximumSubArray(array);
        int sum = arrayList.stream().reduce(0, Integer::sum);
        System.out.println("Sum: " + sum + "\n" + "MaxSubArray: " + arrayList.toString());
    }
}
