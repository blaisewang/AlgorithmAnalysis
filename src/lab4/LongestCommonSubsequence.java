package lab4;

import java.util.Scanner;

/**
 * AlgorithmAnalysis
 * Created by blaisewang on 2017/4/9.
 */
public class LongestCommonSubsequence {

    private static String first;
    private static String second;
    private static int[][] commonArray;
    private static StringBuilder stringBuilder = new StringBuilder();

    private static void longestCommonSubsequence(String first, String second) {
        int firstLength = first.length();
        int secondLength = second.length();
        commonArray = new int[firstLength + 1][secondLength + 1];
        for (int row = 0; row <= firstLength; row++) {
            for (int column = 0; column <= secondLength; column++) {
                if (column == 0 || row == 0) {
                    commonArray[row][column] = 0;
                } else if (first.charAt(row - 1) == second.charAt(column - 1)) {
                    commonArray[row][column] = commonArray[row - 1][column - 1] + 1;
                } else {
                    commonArray[row][column] = commonArray[row - 1][column] >= commonArray[row][column - 1] ? commonArray[row - 1][column] : commonArray[row][column - 1];
                }
            }
        }
    }

    private static void getLCS(int row, int cloumn) {
        if (row == 0 || cloumn == 0) {
            return;
        }

        if (first.charAt(row - 1) != second.charAt(cloumn - 1) || commonArray[row][cloumn] != commonArray[row - 1][cloumn - 1] + 1) {
            if (first.charAt(row - 1) != second.charAt(cloumn - 1) && commonArray[row - 1][cloumn] >= commonArray[row][cloumn - 1]) {
                getLCS(row - 1, cloumn);
            } else {
                getLCS(row, cloumn - 1);
            }
        } else {
            getLCS(row - 1, cloumn - 1);
            stringBuilder.append(first.charAt(row - 1));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("First string:");
        first = scanner.nextLine();
        System.out.println("Second string:");
        second = scanner.nextLine();
        scanner.close();
        longestCommonSubsequence(first, second);
        getLCS(first.length(), second.length());
        System.out.println("Length: " + commonArray[first.length()][second.length()]);
        System.out.println("LCS: " + stringBuilder.toString());
    }
}
