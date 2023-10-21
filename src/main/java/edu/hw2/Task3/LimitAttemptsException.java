package edu.hw2.Task3;

public class LimitAttemptsException extends Exception {

    @Override
    public String getMessage() {
        return "The maximum number of connection attempts has been reached";
    }
}
