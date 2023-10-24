package edu.project1;

import java.util.Random;

public enum Dictionary {
    MEET;

    private static final Random RANDOM = new Random();

    public static String randomWord() {
        return getWordDictionary()[RANDOM.nextInt(getDictionarySize())].toString().toLowerCase();
    }

    private static Dictionary[] getWordDictionary() {
        return Dictionary.values();
    }

    private static int getDictionarySize() {
        return getWordDictionary().length;
    }
}
