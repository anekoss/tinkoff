package edu.project1.result;

import edu.project1.WordGuess;

public final class LostResult extends GuessResult {

    public LostResult(WordGuess wordGuess) {
        super(wordGuess);
    }

    @Override
    public String message() {
        return new FailedGuessResult(wordGuess).message() + "\nYou lost!";
    }
}
