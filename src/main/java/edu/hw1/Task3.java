package edu.hw1;

public class Task3 {

    private Task3() {

    }

    public static boolean isNestable(int[] a, int[] b) {
        if (a.length == 0 && b.length == 0) {
            return false;
        }
        if ((a.length * b.length) == 0) {
            return true;
        }
        int minA = a[0], maxA = a[0], minB = b[0], maxB = b[0];
        for (int j : a) {
            minA = Math.min(minA, j);
            maxA = Math.max(maxA, j);
        }
        for (int j : b) {
            minB = Math.min(minB, j);
            maxB = Math.max(maxB, j);
        }
        return minA > minB || maxA < maxB;
    }
}
