package edu.project3;

public class Main {

    private Main() {

    }

    public static void main(String[] args) {
        LogFileAnalyzer logFileAnalyzer = new LogFileAnalyzer(args);
        logFileAnalyzer.run(args);
    }
}
