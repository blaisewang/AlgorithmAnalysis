package lab2;

/**
 * AlgorithmAnalysis
 * Created by blaisewang on 2017/3/19.
 */
public class QuickSort {
    public static void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    public static int findNthMinimum(int[] array, int number) {
        if (number > array.length || number <= 0) {
            return -1;
        }
        return findNthMinimum(array, 0, array.length - 1, number);
    }

    private static int findNthMinimum(int[] array, int low, int high, int number) {
        int p = partition(array, low, high);
        if (p - low + 1 == number) {
            return array[p];
        }
        if (p - low + 1 > number) {
            return findNthMinimum(array, low, p - 1, number);
        } else {
            return findNthMinimum(array, p + 1, high, number - (p - low + 1));
        }
    }

    private static void sort(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int index = partition(array, low, high);
        sort(array, low, index - 1);
        sort(array, index + 1, high);
    }

    private static int partition(int[] array, int low, int high) {
        int middle = low + (high - low) / 2;
        if (array[middle] > array[high]) {
            swap(array, middle, high);
        }
        if (array[low] > array[high]) {
            swap(array, low, high);
        }
        if (array[middle] > array[low]) {
            swap(array, middle, low);
        }
        int key = array[low];

        while (low < high) {
            while (array[high] >= key && high > low) {
                high--;
            }
            array[low] = array[high];
            while (array[low] <= key && high > low) {
                low++;
            }
            array[high] = array[low];
        }
        array[high] = key;

        return high;
    }

    private static void swap(int[] array, int first, int second) {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }
}
