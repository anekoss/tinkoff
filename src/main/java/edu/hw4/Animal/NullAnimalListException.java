package edu.hw4.Animal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NullAnimalListException extends Exception {
    @Override
    public String getMessage() {
        log.info("This method cannot be applied to a null list");
        return super.getMessage();
    }
}
