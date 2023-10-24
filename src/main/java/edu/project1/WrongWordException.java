package edu.project1;

public class WrongWordException extends java.lang.Exception {
    @Override
    public String getMessage() {
        return "The hidden word cannot be empty. Choose another word!";
    }
}
