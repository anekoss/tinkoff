package edu.hw3.Task1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Task1 {
    private static final char MIN_LOWER_CHAR = 'a';
    private static final char MAX_LOWER_CHAR = 'z';
    private static final char MIN_UPPER_CHAR = 'A';
    private static final char MAX_UPPER_CHAR = 'Z';

    private Task1() {

    }

    public static String atbash(String value) throws NullStringCodedException {
        log.info("encoding of the string {}", value);
        if (value == null) {
            throw new NullStringCodedException();
        }
        char[] codeString = value.toCharArray();
        for (int i = 0; i < codeString.length; i++) {
            if (codeString[i] >= MIN_LOWER_CHAR && codeString[i] <= MAX_LOWER_CHAR) {
                codeString[i] = (char) (MIN_LOWER_CHAR + (MAX_LOWER_CHAR - codeString[i]));
            }
            if (codeString[i] >= MIN_UPPER_CHAR && codeString[i] <= MAX_UPPER_CHAR) {
                codeString[i] = (char) (MIN_UPPER_CHAR + (MAX_UPPER_CHAR - codeString[i]));
            }
        }
        log.info("string encoding completed");
        return new String(codeString);
    }
}
