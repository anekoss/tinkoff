package edu.hw2.Task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.hw2.Task3.DefaultConnectionManager.DEFAULT_PROBABILITY_FAULTY_CONNECTION;

public class FaultyConnection implements Connection {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) throws ConnectionException {
        if (DEFAULT_PROBABILITY_FAULTY_CONNECTION >= Math.random()) {
            throw new ConnectionException("execution " + command + " command failed, bad connection");
        }
    }

    @Override
    public void close() {
        LOGGER.info("closing server connection");
    }
}
