package edu.project1;

import java.util.Scanner;

public class Game {
    private final PlayersProvider playersProvider;
    private final Scanner scanner;
    private final Word word;

    public Game(PlayersProvider playersProvider, Scanner scanner) {
        this.playersProvider = playersProvider;
        this.word = new Word();
        this.scanner = scanner;
    }

    public Game(PlayersProvider playersProvider, Scanner scanner, int maxAttempts) {
        this.playersProvider = playersProvider;
        this.word = new Word(maxAttempts);
        this.scanner = scanner;
    }

    public Game(PlayersProvider playersProvider, Scanner scanner, String word) {
        this.playersProvider = playersProvider;
        this.word = new Word(word);
        this.scanner = scanner;
    }

    public Game(PlayersProvider playersProvider, Scanner scanner, String word, int maxAttempts) {
        this.playersProvider = playersProvider;
        this.word = new Word(word, maxAttempts);
        this.scanner = scanner;
    }

    public void play() throws WrongWordException {
        if (!word.isEmpty()) {
            throw new WrongWordException();
        }
        do {
            playersProvider.getNext();
            Printer.printInvite(playersProvider);
            String guess = scanner.next();
            Printer.printGuess(guess);
            tryGuess(guess);

        }
        while ((!word.checkAttemptsEnded() && !playersProvider.isWinner()) && !playersProvider.isGiveUp()
            && scanner.hasNext());
    }

    private void tryGuess(String guess) {
        if (guess.equals("exit")) {
            playersProvider.giveUp();
            Printer.printGiveUpResult(word);
        } else if (guess.length() == 1 && word.isRepeatGuess(guess.charAt(0))) {
            playersProvider.setPosition();
            Printer.printRepeatGuessResult(word);
        } else if (guess.length() == 1) {
            boolean checkGuess = word.guess(guess.charAt(0));
            if (word.checkAttemptsEnded() && !checkGuess) {
                Printer.printLostResult(word);
            } else if (!checkGuess) {
                Printer.printFailedGuessResult(word);
            } else if (word.checkWin()) {
                playersProvider.setIsWinner();
                Printer.printWinResult(word);
            } else {
                Printer.printSuccessfulResult(word);
            }
        } else {
            playersProvider.setPosition();
            Printer.printRetryResult(word);
        }
    }

    public Word getWord() {
        return word;
    }
}
