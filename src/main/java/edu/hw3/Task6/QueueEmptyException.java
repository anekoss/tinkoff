package edu.hw3.Task6;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QueueEmptyException extends Exception {
    @Override
    public String getMessage() {
        log.info("queue is empty");
        return super.getMessage();
    }
}
