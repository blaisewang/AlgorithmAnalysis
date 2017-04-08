package lab1;

/**
 * AlgorithmAnalysis
 * Created by blaisewang on 2017/3/12.
 */
public class MergeSort {
    public static void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int low, int high) {
        int middle = (low + high) / 2;
        if (low < high) {
            sort(array, low, middle);
            sort(array, middle + 1, high);
            merge(array, low, middle, high);
        }
    }

    private static void merge(int[] array, int low, int middle, int high) {
        int[] resultArray = new int[high - low + 1];
        int leftPosition = low;
        int rightPosition = middle + 1;
        int currentPosition = 0;

        while (leftPosition <= middle && rightPosition <= high) {
            if (array[leftPosition] < array[rightPosition]) {
                resultArray[currentPosition++] = array[leftPosition++];
            } else {
                resultArray[currentPosition++] = array[rightPosition++];
            }
        }

        while (leftPosition <= middle) {
            resultArray[currentPosition++] = array[leftPosition++];
        }

        while (rightPosition <= high) {
            resultArray[currentPosition++] = array[rightPosition++];
        }

        System.arraycopy(resultArray, 0, array, low, resultArray.length);
    }
}
