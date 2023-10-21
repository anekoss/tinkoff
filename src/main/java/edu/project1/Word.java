package edu.project1;

import java.util.Arrays;

public class Word {

    private final String guessWord;
    private final int maxAttempts;
    private int attempts = 0;
    private char[] userAnswer;
    private char[] userChar;

    Word() {
        this.guessWord = Dictionary.randomWord();
        this.maxAttempts = 1;
        userAnswer = new char[guessWord.length()];
        Arrays.fill(userAnswer, '*');
        userChar = new char[this.maxAttempts + guessWord.length()];
    }

    Word(int maxAttempts) {
        this.guessWord = Dictionary.randomWord();
        this.maxAttempts = maxAttempts;
        userAnswer = new char[guessWord.length()];
        Arrays.fill(userAnswer, '*');
        userChar = new char[this.maxAttempts + guessWord.length()];

    }

    public Word(String word) {
        this.guessWord = word;
        this.maxAttempts = 1;
        if (word != null) {
            userAnswer = new char[word.length()];
            Arrays.fill(userAnswer, '*');
            userChar = new char[this.maxAttempts + word.length()];
        }

    }

    public Word(String word, int maxAttempts) {
        this.guessWord = word;
        this.maxAttempts = maxAttempts;
        if (word != null) {
            userAnswer = new char[word.length()];
            Arrays.fill(userAnswer, '*');
            userChar = new char[this.maxAttempts + word.length()];
        }
    }

    boolean guess(char guess) {
        boolean check = false;
        for (int i = 0; i < guessWord.length(); i++) {
            if (guessWord.charAt(i) == guess) {
                this.userAnswer[i] = guess;
                check = true;
            }
        }
        if (!check) {
            this.attempts++;
        }
        return check;
    }

    boolean isRepeatGuess(char guess) {
        for (int i = 0; i < userChar.length; i++) {
            if (userChar[i] == guess) {
                return true;
            }
            if (userChar[i] == 0) {
                userChar[i] = guess;
                return false;
            }
        }
        return false;
    }

    boolean checkAttemptsEnded() {
        return maxAttempts == attempts;
    }

    boolean checkWin() {
        return guessWord.equals(getUserAnswer());
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

    public boolean isEmpty() {
        return guessWord != null && guessWord.length() > 0;
    }
}
