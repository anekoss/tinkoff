package edu.hw4;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmptyArrayException extends Exception {
    @Override
    public String getMessage() {
        log.info("This method cannot be applied to an empty array");
        return super.getMessage();
    }
}
