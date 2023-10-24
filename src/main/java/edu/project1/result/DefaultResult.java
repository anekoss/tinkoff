package edu.project1.result;

import edu.project1.WordGuess;

public final class DefaultResult extends GuessResult {

    public DefaultResult(WordGuess wordGuess) {
        super(wordGuess);
    }

    @Override
    public String message() {
        return "The word: " + wordGuess.getUserAnswer();
    }

}
