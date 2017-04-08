package lab1;

/**
 * AlgorithmAnalysis
 * Created by blaisewang on 2017/3/12.
 */
public class InsertionSort {
    public static void sort(int[] array) {
        for (int index = 1; index < array.length; index++) {
            int target = array[index];
            int currentPosition;
            for (currentPosition = index; currentPosition > 0 && target < array[currentPosition - 1]; currentPosition--) {
                array[currentPosition] = array[currentPosition - 1];
            }
            array[currentPosition] = target;
        }
    }
}
