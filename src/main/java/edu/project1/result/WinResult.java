package edu.project1.result;

import edu.project1.WordGuess;

public final class WinResult extends GuessResult {

    public WinResult(WordGuess wordGuess) {
        super(wordGuess);
    }

    @Override
    public String message() {
        return new SuccessfulGuessResult(wordGuess) + "\nYou won!";
    }
}
