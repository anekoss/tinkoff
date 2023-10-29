package edu.hw3.Task4;

import java.util.LinkedHashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Task4 {

    private static final LinkedHashMap<String, Integer> ROMAN_MAP = new LinkedHashMap<>();
    private static final int ROMAN_M_TO_ARABIC = 1000;
    private static final int ROMAN_CM_TO_ARABIC = 900;
    private static final int ROMAN_D_TO_ARABIC = 500;
    private static final int ROMAN_CD_TO_ARABIC = 400;
    private static final int ROMAN_C_TO_ARABIC = 100;
    private static final int ROMAN_XC_TO_ARABIC = 90;
    private static final int ROMAN_L_TO_ARABIC = 50;
    private static final int ROMAN_XL_TO_ARABIC = 40;
    private static final int ROMAN_X_TO_ARABIC = 10;
    private static final int ROMAN_IX_TO_ARABIC = 9;
    private static final int ROMAN_V_TO_ARABIC = 5;
    private static final int ROMAN_IV_TO_ARABIC = 4;
    private static final int ROMAN_I_TO_ARABIC = 1;

    private Task4() {
    }

    static {
        ROMAN_MAP.put("M", ROMAN_M_TO_ARABIC);
        ROMAN_MAP.put("CM", ROMAN_CM_TO_ARABIC);
        ROMAN_MAP.put("D", ROMAN_D_TO_ARABIC);
        ROMAN_MAP.put("CD", ROMAN_CD_TO_ARABIC);
        ROMAN_MAP.put("C", ROMAN_C_TO_ARABIC);
        ROMAN_MAP.put("XC", ROMAN_XC_TO_ARABIC);
        ROMAN_MAP.put("L", ROMAN_L_TO_ARABIC);
        ROMAN_MAP.put("XL", ROMAN_XL_TO_ARABIC);
        ROMAN_MAP.put("X", ROMAN_X_TO_ARABIC);
        ROMAN_MAP.put("IX", ROMAN_IX_TO_ARABIC);
        ROMAN_MAP.put("V", ROMAN_V_TO_ARABIC);
        ROMAN_MAP.put("IV", ROMAN_IV_TO_ARABIC);
        ROMAN_MAP.put("I", ROMAN_I_TO_ARABIC);
    }

    public static String convertToRoman(Integer arabicNumber) throws ConvertToRomanException {
        log.info("getting the Roman number from the Arabic execution");
        if (checkArabicNumber(arabicNumber)) {
            throw new ConvertToRomanException();
        }
        String romanNumber = "";

        int number = arabicNumber;
        for (Map.Entry<String, Integer> e : ROMAN_MAP.entrySet()) {
            while (number >= e.getValue()) {
                romanNumber = romanNumber.concat(e.getKey());
                number = number - e.getValue();
            }
        }
        log.info("receipt completed");
        return romanNumber;
    }

    private static boolean checkArabicNumber(Integer value) {
        return value == null || value == 0;
    }

}
