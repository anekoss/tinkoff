package edu.project1.result;

import edu.project1.Word;

public final class GiveUpResult extends GuessResult {

    public GiveUpResult(Word word) {
        super(word);
    }

    @Override
    public String message() {
        return "You give up!";
    }
}
