package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private Task2() {

    }

    public static List<String> clusterize(String value) {
        List<String> list = new ArrayList<>();
        if (value == null) {
            return list;
        }
        int cnt = 0;
        int index = 0;
        for (
            int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == '(') {
                cnt++;
            } else if (value.charAt(i) == ')') {
                cnt--;
                if (cnt == 0) {
                    list.add(value.substring(index, i + 1));
                }
            }
            if (cnt == 0) {
                index = i + 1;
            }
        }
        return list;
    }
}
