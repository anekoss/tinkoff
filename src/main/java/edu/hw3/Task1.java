package edu.hw3;

import java.util.ArrayList;

public class Task1 {
    private Task1() {

    }

    private static final ArrayList<Character> lang = new ArrayList<>();

    static {
        for (char ch = 'A'; ch <= 'z'; ch++) {
            lang.add(ch);
            if (ch == 'Z') {
                ch = 'a' - 1;
            }
        }
    }

    public static String atbash(String s) {
        char[] result = s.toCharArray();
        for (int i = 0; i < result.length; i++) {
            int index = lang.indexOf(result[i]);
            if (index != -1) {
                if (index >= 26) {
                    result[i] = lang.get(52 - (index - 25));
                } else {
                    result[i] = lang.get(25 - index);
                }
            }
        }
        return new String(result);
    }
}
