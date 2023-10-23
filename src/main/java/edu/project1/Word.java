package edu.project1;

public class Word {
    private final String resultWord;

    Word() {
        this.resultWord = Dictionary.randomWord();
    }

    public Word(String resultWord) throws WrongWordException {
        if (isWordEmpty(resultWord)) {
            throw new WrongWordException();
        }
        this.resultWord = resultWord;
    }

    public String guessChar(char guess) {
        return resultWord.replaceAll("[^" + guess + "]", "*");
    }

    public boolean equalsGuessWord(char[] guessWord) {
        return resultWord.equals(new String(guessWord));
    }

    public boolean isWordEmpty(String value) {
        return value == null || value.length() == 0;
    }

    public int getSizeWord() {
        return resultWord.length();
    }

}
