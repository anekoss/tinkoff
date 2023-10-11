package edu.hw1;

import static edu.hw1.Task6.MIN_TWO_DIGIT_NUM;

public class Task5 {

    private Task5() {

    }

    public static boolean isPalindromeDescendant(int n) {
        if (isPalindrome(n)) {
            return true;
        }
        int num = n;
        num = getDescendant(num);
        while (num / MIN_TWO_DIGIT_NUM >= 1) {
            if (isPalindrome(num)) {
                return true;
            }
            num = getDescendant(num);
        }
        return false;

    }

    public static int getDescendant(int n) {
        char[] s = String.valueOf(n).toCharArray();
        StringBuilder newS = new StringBuilder();
        for (int i = 0; i < s.length; i += 2) {
            if (i + 1 <= s.length - 1) {

                newS.append(Character.digit(s[i], MIN_TWO_DIGIT_NUM) + Character.digit(s[i + 1], MIN_TWO_DIGIT_NUM));
            } else if (i <= s.length - 1) {
                newS.append(Character.digit(s[i], MIN_TWO_DIGIT_NUM));
            }
        }
        return Integer.parseInt(newS.toString());
    }

    public static boolean isPalindrome(int n) {
        char[] s = String.valueOf(n).toCharArray();
        for (int i = 0; i < s.length / 2; i++) {
            if (s[i] != s[s.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

}
