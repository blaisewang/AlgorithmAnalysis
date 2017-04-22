package lab5;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Scanner;

/**
 * AlgorithmAnalysis
 * Created by blaisewang on 2017/4/22.
 */

public class MatrixChainMultiplication {
    private static int[][] s;
    private static int[] p;

    private static String getResult(String[] sequence) {
        int length = sequence.length;
        p = new int[length];
        s = new int[length][length];

        for (int j = 0; j < length; j++) {
            try {
                p[j] = Integer.parseInt(sequence[j]);
            } catch (NumberFormatException ignored) {
                return null;
            }
        }

        return matrixChainOrder(length) + "\n" + optimalParenthesis(1, length - 1);
    }

    private static int matrixChainOrder(int length) {
        int[][] m = new int[length][length];

        for (int i = 1; i < p.length; i++) {
            m[i][i] = 0;
        }

        for (int l = 1; l < p.length - 1; l++) {
            for (int i = 1; i < p.length - l; i++) {
                int j = i + l;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int q = m[i][k] + m[k + 1][j] + (p[i - 1] * p[k] * p[j]);
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }

        return m[1][length - 1];
    }

    private static String optimalParenthesis(int i, int j) {
        if (i != j) {
            return "(".concat(optimalParenthesis(i, s[i][j]).concat(optimalParenthesis(s[i][j] + 1, j))).concat(")");
        } else {
            return "A" + i;
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // manual input
        //System.out.println(MatrixChainMultiplication.getResult(new Scanner(System.in).nextLine().split(" ")));

        // Randomly generate
        byte[] bytes = new byte[new Scanner(System.in).nextInt()];
        SecureRandom.getInstanceStrong().nextBytes(bytes);
        StringBuilder stringBuilder = new StringBuilder();

        for (byte aByte : bytes) {
            stringBuilder.append(Math.abs((aByte) > 10 ? Math.abs(aByte) : Math.abs(aByte) + 10)).append(" ");
        }

        System.out.println(stringBuilder);
        System.out.println(MatrixChainMultiplication.getResult(stringBuilder.toString().split(" ")));
    }
}
