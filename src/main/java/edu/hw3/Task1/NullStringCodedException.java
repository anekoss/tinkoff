package edu.hw3.Task1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NullStringCodedException extends Exception {
    @Override
    public String getMessage() {
        log.info("null string cannot be coded");
        return super.getMessage();
    }
}
