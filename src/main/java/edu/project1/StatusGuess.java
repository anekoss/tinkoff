package edu.project1;

import edu.project1.result.DefaultResult;
import edu.project1.result.GuessResult;

public class StatusGuess {
    private final WordGuess wordGuess;
    private Status status;
    private GuessResult guessResult;
    private final GuessResultBuilder guessResultBuilder = new GuessResultBuilder();

    StatusGuess(WordGuess word) {
        this.wordGuess = word;
        this.status = Status.GUESS;
        this.guessResult = new DefaultResult(wordGuess);
    }

    public void tryGuess(String guess) {
        setGuessStatus(guess);
        guessResult = guessResultBuilder.build(status, wordGuess);
    }

    public void setGuessCharacterStatus(String guess) {
        boolean checkGuess = wordGuess.guessCharInWord(guess);
        if (wordGuess.checkAttemptsEnded()) {
            status = Status.LOST;
        } else if (!checkGuess) {
            status = Status.NOT_GUESS;
        } else if (wordGuess.checkWin()) {
            status = Status.WON;
        } else {
            status = Status.GUESS;
        }
    }

    public void setGuessStatus(String guess) {
        if (wordGuess.isExit(guess)) {
            status = Status.GIVE_UP;
        } else if (wordGuess.isRepeatGuess(guess)) {
            status = Status.REPEAT_GUESS;
        } else if (wordGuess.isChar(guess)) {
            setGuessCharacterStatus(guess);
        } else {
            status = Status.RETRY_GUESS;
        }
    }

    public boolean isEndGame() {
        return status == Status.GIVE_UP
            || status == Status.LOST
            || status == Status.WON;
    }

    public Status getStatus() {
        return status;
    }

    public WordGuess getWordGuess() {
        return wordGuess;
    }

    public GuessResult getGuessResult() {
        return guessResult;
    }
}
