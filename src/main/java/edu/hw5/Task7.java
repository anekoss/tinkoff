package edu.hw5;

import java.util.regex.Pattern;

public class Task7 {
    private final Pattern thirdCharZeroLengthMoreTwo = Pattern.compile("^[0,1]{2}0[0,1]*$");
    private final Pattern sameCharStartAndEnd = Pattern.compile("^([0,1])[0,1]*\\1$|^[0,1]$");
    private final Pattern lengthMoreZeroLessFour = Pattern.compile("^[0,1]{1,3}$");

    public boolean checkThirdCharZeroLengthMoreTwo(String string) throws InputErrorException {
        if (string == null) {
            throw new InputErrorException();
        }
        return thirdCharZeroLengthMoreTwo.matcher(string).find();
    }

    public boolean checkSameCharStartAndEnd(String string) throws InputErrorException {
        if (string == null) {
            throw new InputErrorException();
        }
        return sameCharStartAndEnd.matcher(string).find();
    }

    public boolean checkLengthMoreZeroLessFour(String string) throws InputErrorException {
        if (string == null) {
            throw new InputErrorException();
        }
        return lengthMoreZeroLessFour.matcher(string).find();
    }
}
