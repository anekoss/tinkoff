package edu.hw1;

public class Task4 {
    private Task4() {
    }

    public static String fixString(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length - 1; i = i + 2) {
            char temp = arr[i];
            arr[i] = arr[i + 1];
            arr[i + 1] = temp;
        }
        return new String(arr);

    }

}
