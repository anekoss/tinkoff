package edu.hw1;

public class Task1 {
    private static final int COUNTMININHOURS = 60;

    private Task1() {
    }

    public static int minutesToSeconds(String s) {
        String[] arr = s.replaceAll(" ", "").split(":");
        try {
            if (arr.length != 2 || Integer.parseInt(arr[1]) < 0 || Integer.parseInt(arr[1]) >= COUNTMININHOURS
                || Integer.parseInt(arr[0]) < 0) {
                return -1;
            }
        } catch (NumberFormatException ex) {
            return -1;
        }
        return Integer.parseInt(arr[0]) * COUNTMININHOURS + Integer.parseInt(arr[1]);
    }
}
