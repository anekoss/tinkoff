package edu.project1;

import java.util.Scanner;

public class Game {
    private final PlayersProvider playersProvider;
    private final Scanner scanner;
    private final Printer printer;
    private final StatusGuess statusGuess;

    public Game(PlayersProvider playersProvider, Scanner scanner, StatusGuess statusGuess) {
        this.playersProvider = playersProvider;
        this.scanner = scanner;
        this.statusGuess = statusGuess;
        this.printer = new Printer();
    }

    public Game(PlayersProvider playersProvider, Scanner scanner, StatusGuess statusGuess, Printer printer) {
        this.playersProvider = playersProvider;
        this.scanner = scanner;
        this.statusGuess = statusGuess;
        this.printer = printer;
    }

    public void play() {
        while (!statusGuess.isEndGame() && scanner.hasNext()) {
            playersProvider.getNext(statusGuess.getStatus());
            printer.printInvite(playersProvider);
            String guess = scanner.next();
            printer.printGuess(guess);
            statusGuess.tryGuess(guess);
            printer.printResult(statusGuess.getGuessResult());
        }
    }

}
