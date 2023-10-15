package edu.hw1;

public class Task1 {
    private static final int COUNT_MIN_IN_HOURS = 60;
    private static final int SIZE_ARR = 2;

    private Task1() {
    }

    public static int minutesToSeconds(String s) {
        if (s == null) {
            return -1;
        }
        String[] arr = s.replaceAll(" ", "").split(":");
        try {
            if (arr.length != SIZE_ARR || Integer.parseInt(arr[1]) < 0 || Integer.parseInt(arr[1]) >= COUNT_MIN_IN_HOURS
                || Integer.parseInt(arr[0]) < 0) {
                return -1;
            }
        } catch (NumberFormatException ex) {
            return -1;
        }
        return Integer.parseInt(arr[0]) * COUNT_MIN_IN_HOURS + Integer.parseInt(arr[1]);
    }
}
