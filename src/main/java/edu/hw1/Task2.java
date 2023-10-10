package edu.hw1;

public class Task2 {

    private static final int NUMSYS = 10;

    private Task2() {
    }

    public static int countDigits(Integer n) {
        int cnt = 0;
        int num = n;
        do {
            cnt++;
            num = num / NUMSYS;
        }
        while (Math.abs(num) > 0);
        return cnt;
    }

}
