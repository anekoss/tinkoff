package edu.hw5;

import java.util.regex.Pattern;

public class Task8 {

    private final Pattern oddLength = Pattern.compile("^[01]((00)|(01)|(10)|(11))*$");
    private final Pattern oddLengthStart1OrEvenLengthStart0 =
        Pattern.compile("^(0((00)|(01)|(10)|(11))*|1[0,1]((00)|(01)|(10)|(11))*)$");

    private final Pattern count0DivBy3 =
        Pattern.compile("^(1*01*01*01*)*$|^$");

    private final Pattern allExcept11And111 =
        Pattern.compile("^(?!11$)(?!111$)([01])*$");

    private final Pattern anyOdd1 =
        Pattern.compile("^1(11|01)*$");

    private final Pattern moreTwo0AndOne1 =
        Pattern.compile("^(0+10+|00+10*|0*100+)$");

    private final Pattern noNeighbour1 =
        Pattern.compile("^((0*1(0+|$))*|0*)$");

    public boolean checkOddLength(String string) throws InputErrorException {
        isWordNull(string);
        return oddLength.matcher(string).find();
    }

    public boolean checkOddLengthStartOneOrEvenLengthStartZero(String string) throws InputErrorException {
        isWordNull(string);
        return oddLengthStart1OrEvenLengthStart0.matcher(string).find();
    }

    public boolean checkCountZeroDivByTree(String string) throws InputErrorException {
        isWordNull(string);
        return count0DivBy3.matcher(string).find();
    }

    public boolean checkAllExcept11And111(String string) throws InputErrorException {
        isWordNull(string);
        return allExcept11And111.matcher(string).find();
    }

    public boolean checkAnyOdd1(String string) throws InputErrorException {
        isWordNull(string);
        return anyOdd1.matcher(string).find();
    }

    public boolean checkMoreTwo0AndOne1(String string) throws InputErrorException {
        isWordNull(string);
        return moreTwo0AndOne1.matcher(string).find();
    }

    public boolean checkNoNeighbour1(String string) throws InputErrorException {
        isWordNull(string);
        return noNeighbour1.matcher(string).find();
    }

    private void isWordNull(String date) throws InputErrorException {
        if (date == null) {
            throw new InputErrorException();
        }
    }
}
