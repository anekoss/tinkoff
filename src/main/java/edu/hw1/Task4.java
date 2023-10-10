package edu.hw1;

public class Task4 {
    private Task4() {
    }

    public static String fixString(String s) {
        String[] arr = s.split("");
        for (int i = 0; i < arr.length - 1; i = i + 2) {
            String temp = arr[i];
            arr[i] = arr[i + 1];
            arr[i + 1] = temp;
        }
        return String.join("", arr);
    }

}
