package edu.project1.result;

import edu.project1.Word;

public final class LostResult extends GuessResult {

    public LostResult(Word word) {
        super(word);
    }

    @Override
    public String message() {
        return "You lost!";
    }
}
