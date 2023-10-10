package edu.hw1;

public class Task7 {

    public Task7() {

    }

    public static int rotateLeft(int n, int shift) {
        String binaryNum = Integer.toBinaryString(n);
        for (int i = 0; i < shift; i++) {
            binaryNum = binaryNum.substring(1) + binaryNum.charAt(0);
        }
        return Integer.parseInt(binaryNum, 2);
    }

    public static int rotateRight(int n, int shift) {
        String binaryNum = Integer.toBinaryString(n);

        for (int i = 0; i < shift; i++) {
            binaryNum = binaryNum.substring(binaryNum.length() - 1) +
                binaryNum.substring(0, binaryNum.length() - 1);

        }
        return Integer.parseInt(binaryNum, 2);

    }
}
