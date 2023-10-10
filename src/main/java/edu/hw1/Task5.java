package edu.hw1;

public class Task5 {

    private Task5() {

    }

    private static final int NUMSYS = 10;

    public static boolean isPalindromeDescendant(int n) {
        if (isPalindrome(n)) {
            return true;
        }
        int num = n;
        num = getDescendant(num);
        while (num / NUMSYS >= 1) {
            if (isPalindrome(num)) {
                return true;
            }
            num = getDescendant(num);
        }
        return false;

    }

    public static int getDescendant(int n) {
        String[] s = String.valueOf(n).split("");
        StringBuilder newS = new StringBuilder();
        for (int i = 0; i < s.length; i += 2) {
            if (i + 1 <= s.length - 1) {

                newS.append(Integer.parseInt(s[i]) + Integer.parseInt(s[i + 1]));
            } else if (i <= s.length - 1) {

                newS.append(Integer.parseInt(s[i]));
            }
        }
        return Integer.parseInt(newS.toString());
    }

    public static boolean isPalindrome(int n) {
        String[] s = String.valueOf(n).split("");
        for (int i = 0; i < s.length / 2; i++) {
            if (!s[i].equals(s[s.length - 1 - i])) {
                return false;
            }
        }
        return true;
    }

}
