package edu.hw8.Task3;

import org.jetbrains.annotations.NotNull;

public class PasswordIterator {
    private PasswordIterator() {

    }

    private final static String DEFAULT_NEXT_STRING_VALUE = "";

    public static int countCombination(int lengthCombination, int sizeAlphabet) {
        if (lengthCombination <= 0 || sizeAlphabet <= 0) {
            return 0;
        }
        int countCombination = 1;
        for (int i = 0; i < lengthCombination; i++) {
            countCombination *= sizeAlphabet;
        }
        return countCombination;
    }

    public static String nextPassword(int numberNextPassword, char @NotNull [] symbols) {
        if (numberNextPassword < 0) {
            return DEFAULT_NEXT_STRING_VALUE;
        }
        StringBuilder password = new StringBuilder();
        int numberPassword = numberNextPassword;
        do {
            int index = numberPassword % symbols.length;
            password.append(symbols[index]);
            numberPassword = numberPassword / symbols.length;
        }
        while (numberPassword > 0);
        return password.toString();
    }
}
