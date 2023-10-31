package edu.hw3.Task5;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmptyContactArrayException extends Exception {
    @Override
    public String getMessage() {
        log.info("empty array cannot be sorted");
        return super.getMessage();
    }
}
