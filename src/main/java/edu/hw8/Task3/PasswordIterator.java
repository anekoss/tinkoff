package edu.hw8.Task3;

import org.jetbrains.annotations.NotNull;

public class PasswordIterator {
    private final static String DEFAULT_NEXT_STRING_VALUE = "";

    public static int countCombination(int lengthCombination, int sizeAlphabet) {
        if (lengthCombination <= 0 || sizeAlphabet <= 0) {
            return 0;
        }
        int countCompination = 1;
        for (int i = 0; i < lengthCombination; i++) {
            countCompination *= sizeAlphabet;
        }
        return countCompination;
    }

    public static String nextPassword(int numberNextPassword, char @NotNull [] symbols) {
        if (numberNextPassword < 0) {
            return DEFAULT_NEXT_STRING_VALUE;
        }
        StringBuilder password = new StringBuilder();
        do {
            int index = numberNextPassword % symbols.length;
            password.append(symbols[index]);
            numberNextPassword = numberNextPassword / symbols.length;
        }
        while (numberNextPassword > 0);
        return password.toString();
    }
}
