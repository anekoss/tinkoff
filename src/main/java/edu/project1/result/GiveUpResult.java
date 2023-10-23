package edu.project1.result;

import edu.project1.WordGuess;

public final class GiveUpResult extends GuessResult {

    public GiveUpResult(WordGuess wordGuess) {
        super(wordGuess);
    }

    @Override
    public String message() {
        return "You give up!";
    }
}
