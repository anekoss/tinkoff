package edu.hw3.Task4;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConvertToRomanException extends Exception {
    @Override
    public String getMessage() {
        log.info("this value cannot be converted to a Roman number");
        return super.getMessage();
    }
}
