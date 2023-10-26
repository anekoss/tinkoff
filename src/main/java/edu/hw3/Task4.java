package edu.hw3;

import java.util.LinkedHashMap;
import java.util.Map;

public class Task4 {
    private static LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

    static {
        map.put("M", 1000);
        map.put("CM", 900);
        map.put("D", 500);
        map.put("CD", 400);
        map.put("C", 100);
        map.put("XC", 90);
        map.put("L", 50);
        map.put("XL", 40);
        map.put("X", 10);
        map.put("IX", 9);
        map.put("V", 5);
        map.put("IV", 4);
        map.put("I", 1);
    }

    public static String convertToRoman(int number) {
        String result = "";
        for (String e : map.keySet()) {
            while (number >= map.get(e)) {
                result = result + e;
                number = number - map.get(e);
            }
        }
        return result;
    }
}
