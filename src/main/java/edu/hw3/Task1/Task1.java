package edu.hw3.Task1;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Task1 {
    private Task1() {

    }

    private static final List<Character> ALPHABET = new ArrayList<>();

    static {
        for (char ch = 'a'; ch <= 'z'; ch++) {
            ALPHABET.add(ch);
        }
    }

    public static String atbash(String value) {
        log.info("encoding of the string {}", value);
        if (value == null) {
            log.info("null string cannot be coded");
            return null;
        }
        char[] codeString = value.toCharArray();
        for (int i = 0; i < codeString.length; i++) {
            int index = ALPHABET.indexOf(Character.toLowerCase(codeString[i]));
            if (index != -1) {
                if (Character.isLowerCase(codeString[i])) {
                    codeString[i] = ALPHABET.get(ALPHABET.size() - index - 1);
                } else {
                    codeString[i] = Character.toUpperCase(ALPHABET.get(ALPHABET.size() - index - 1));
                }
            }
        }
        log.info("string encoding completed");
        return new String(codeString);
    }
}
