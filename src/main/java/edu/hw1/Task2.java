package edu.hw1;

import static edu.hw1.Task6.MIN_TWO_DIGIT_NUM;

public class Task2 {

    private Task2() {
    }

    public static int countDigits(int n) {
        int cnt = 0;
        int num = n;
        do {
            cnt++;
            num = num / MIN_TWO_DIGIT_NUM;
        }
        while (Math.abs(num) > 0);
        return cnt;
    }

}
