package edu.hw4;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundAnimalException extends Exception {
    @Override
    public String getMessage() {
        log.info("This method cannot be applied to an animal with a null parameter");
        return super.getMessage();
    }
}
