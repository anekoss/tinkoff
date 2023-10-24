package edu.project1;

import java.util.Arrays;
import java.util.LinkedHashSet;

public class WordGuess {
    private final Word word;
    private final int maxAttempts;

    private int attempts = 0;
    private char[] userAnswer;
    private static final String GIVE_UP = "exit";
    private final LinkedHashSet<Character> userChar = new LinkedHashSet<>();

    public WordGuess(Word word, int maxAttempts) {
        this.word = word;
        this.maxAttempts = maxAttempts;
        fillUserData();
    }

    public WordGuess() {
        this.word = new Word();
        this.maxAttempts = 1;
        fillUserData();
    }

    public WordGuess(int maxAttempts) {
        this.word = new Word();
        this.maxAttempts = maxAttempts;
        fillUserData();

    }

    public WordGuess(String valueWord) throws WrongWordException {
        this.word = new Word(valueWord);
        this.maxAttempts = 1;
        fillUserData();
    }

    public WordGuess(String valueWord, int maxAttempts) throws WrongWordException {
        this.word = new Word(valueWord);
        this.maxAttempts = maxAttempts;
        fillUserData();
    }

    private void fillUserData() {
        userAnswer = new char[word.getSizeWord()];
        Arrays.fill(userAnswer, '*');
    }

    boolean checkAttemptsEnded() {
        return maxAttempts == attempts;
    }

    boolean isRepeatGuess(String guess) {
        return isChar(guess) && !userChar.add(guess.charAt(0));
    }

    boolean isExit(String guess) {
        return GIVE_UP.equals(guess);
    }

    boolean isChar(String guess) {
        return guess.length() == 1;
    }

    boolean guessCharInWord(String guess) {
        String wordPattern = word.guessChar(guess.charAt(0));
        boolean checkGuess = false;
        for (int i = 0; i < userAnswer.length; i++) {
            if (wordPattern.charAt(i) != '*' && userAnswer[i] == '*') {
                userAnswer[i] = wordPattern.charAt(i);
                checkGuess = true;
            }
        }
        if (!checkGuess) {
            attempts++;
        }
        return checkGuess;
    }

    boolean checkWin() {
        return word.equalsGuessWord(userAnswer);
    }

    public int getAttempts() {
        return attempts;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public String getUserAnswer() {
        return String.valueOf(userAnswer);
    }

}
