package edu.hw1;

public class Task1 {
    private Task1() {
    }

    public static int minutesToSeconds(String s) {
        String[] arr = s.replaceAll(" ", "").split(":");
        try {
            if (arr.length != 2 || Integer.parseInt(arr[1]) < 0 || Integer.parseInt(arr[1]) >= 60 ||
                Integer.parseInt(arr[0]) < 0) {
                return -1;
            }
        } catch (NumberFormatException ex) {
            return -1;
        }
        return Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);
    }
}
