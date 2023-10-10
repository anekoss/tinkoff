package edu.hw1;

public class Task2 {

    private Task2() {
    }

    public static int countDigits(Integer n) {
        int cnt = 0;
        do {
            cnt++;
            n = n / 10;
        }
        while (n > 0);
        return cnt;
    }

}
