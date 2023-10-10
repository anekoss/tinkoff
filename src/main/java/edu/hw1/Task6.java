package edu.hw1;

import java.util.Arrays;

public class Task6 {

    public Task6() {
    }

    public static int countK(int n) {
        if (n >= 10000 || n <= 1000 ||
            (n / 1000 == (n / 100) % 10 && (n / 100) % 10 == (n % 100) / 10 && (n % 100) / 10 == n % 10)) {
            return -1;
        }
        int max;
        int min;
        int cnt = 0;
        while (n != 6174) {
            int[] arr = new int[4];
            arr[0] = n / 1000;
            arr[1] = (n / 100) % 10;
            arr[2] = (n % 100) / 10;
            arr[3] = n % 10;
            Arrays.sort(arr);
            max = arr[3] * 1000 + arr[2] * 100 + arr[1] * 10 + arr[0];
            min = arr[0] * 1000 + arr[1] * 100 + arr[2] * 10 + arr[3];
            cnt++;
            n = max - min;
        }
        return cnt;
    }
}
