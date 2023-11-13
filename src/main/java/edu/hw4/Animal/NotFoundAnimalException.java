package edu.hw4.Animal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundAnimalException extends Exception {
    @Override
    public String getMessage() {
        log.info("No animal with these parameters in the list");
        return super.getMessage();
    }
}
