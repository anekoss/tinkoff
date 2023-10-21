package edu.project1.result;

import edu.project1.Word;

public final class DefaultResult extends GuessResult {

    public DefaultResult(Word word) {
        super(word);
    }

    @Override
    public String message() {
        return "The word: " + word.getUserAnswer();
    }

}
