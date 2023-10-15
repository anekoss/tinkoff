package edu.hw1;

import java.util.Arrays;

public class Task6 {

    public static final int MIN_TWO_DIGIT_NUM = 10;
    private static final int SIZE_ARR = 4;
    private static final int CONST_K = 6174;

    private Task6() {
    }

    public static int countK(int n) {
        if (n < Math.pow(MIN_TWO_DIGIT_NUM, SIZE_ARR) && n > Math.pow(MIN_TWO_DIGIT_NUM, SIZE_ARR - 1)) {
            char[] arr = String.valueOf(n).toCharArray();
            int cnt = 0;
            for (int i = 0; i < SIZE_ARR - 1; i++) {
                if (arr[i] == arr[i + 1]) {
                    cnt++;
                }
            }
            if (cnt == SIZE_ARR - 1) {
                return -1;
            }
            int max;
            int min;
            cnt = 0;
            int num = n;
            while (num != CONST_K) {
                Arrays.sort(arr);
                min = 0;
                max = 0;
                for (int i = 0; i < arr.length; i++) {
                    min = min * MIN_TWO_DIGIT_NUM + arr[i];
                    max = max * MIN_TWO_DIGIT_NUM + arr[SIZE_ARR - 1 - i];
                }
                cnt++;
                num = max - min;
                arr = String.valueOf(num).toCharArray();

            }
            return cnt;
        }
        return -1;
    }
}
