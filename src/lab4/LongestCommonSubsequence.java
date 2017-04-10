package lab4;

import java.util.Scanner;

/**
 * AlgorithmAnalysis
 * Created by blaisewang on 2017/4/9.
 */
public class LongestCommonSubsequence {

    private static String first;
    private static String second;
    private static int firstLength;
    private static int secondLength;
    private static int[][] table;
    private static StringBuilder stringBuilder = new StringBuilder();

    private static String getLCS(String first, String second) {
        stringBuilder = new StringBuilder();
        longestCommonSubsequence(first, second);
        getLCS(firstLength, secondLength);
        return stringBuilder.toString();
    }

    private static void longestCommonSubsequence(String firstString, String secondString) {
        first = firstString;
        second = secondString;
        firstLength = first.length();
        secondLength = second.length();
        table = new int[firstLength + 1][secondLength + 1];
        for (int row = 0; row <= firstLength; row++) {
            for (int column = 0; column <= secondLength; column++) {
                if (row != 0 && column != 0) {
                    if (first.charAt(row - 1) != second.charAt(column - 1)) {
                        table[row][column] = table[row - 1][column] >= table[row][column - 1] ? table[row - 1][column] : table[row][column - 1];
                    } else {
                        table[row][column] = table[row - 1][column - 1] + 1;
                    }
                }

            }
        }
    }

    private static void getLCS(int row, int column) {
        if (row == 0 || column == 0) {
            return;
        }

        if (first.charAt(row - 1) != second.charAt(column - 1) || table[row][column] != table[row - 1][column - 1] + 1) {
            if (first.charAt(row - 1) != second.charAt(column - 1) && table[row - 1][column] >= table[row][column - 1]) {
                getLCS(row - 1, column);
            } else {
                getLCS(row, column - 1);
            }
        } else {
            getLCS(row - 1, column - 1);
            stringBuilder.append(first.charAt(row - 1));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("First string:");
        String first = scanner.nextLine();
        System.out.println("Second string:");
        String second = scanner.nextLine();
        scanner.close();
        String LCS = getLCS(first, second);
        System.out.println("LCS: " + LCS + "\nLength: " + LCS.length());
        System.out.println("d(first, second): " + (first.length() - LCS.length()));
    }
}
