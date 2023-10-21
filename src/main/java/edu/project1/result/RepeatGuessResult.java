package edu.project1.result;

import edu.project1.Word;

public final class RepeatGuessResult extends GuessResult {
    public RepeatGuessResult(Word word) {
        super(word);
    }

    @Override
    public String message() {
        return "This guess has already been entered. Please enter another character.";
    }
}
