package edu.hw1;

import java.util.Arrays;

public class Task6 {

    private static final int MIN2DIGITNUM = 10;

    private static final int SIZENUM = 4;
    private static final int CONSTK = 6174;

    private Task6() {
    }

    public static int countK(int n) {
        if (n < Math.pow(MIN2DIGITNUM, SIZENUM) && n > Math.pow(MIN2DIGITNUM, SIZENUM - 1)) {
            int[] arr = Arrays.stream(String.valueOf(n).split("")).mapToInt(Integer::parseInt).toArray();
            int cnt = 0;
            for (int i = 0; i < SIZENUM - 1; i++) {
                if (arr[i] == arr[i + 1]) {
                    cnt++;
                }
            }
            if (cnt == SIZENUM - 1) {
                return -1;
            }
            int max;
            int min;
            cnt = 0;
            int num = n;
            while (num != CONSTK) {
                Arrays.sort(arr);
                min = 0;
                max = 0;
                for (int i = 0; i < arr.length; i++) {
                    min = min * MIN2DIGITNUM + arr[i];
                    max = max * MIN2DIGITNUM + arr[SIZENUM - 1 - i];
                }
                cnt++;
                num = max - min;
                arr = Arrays.stream(String.valueOf(num).split("")).mapToInt(Integer::parseInt).toArray();

            }
            return cnt;
        }
        return -1;
    }
}
