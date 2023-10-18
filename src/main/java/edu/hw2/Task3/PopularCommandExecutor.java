package edu.hw2.Task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;

    private static final Logger LOGGER = LogManager.getLogger();
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() throws ConnectionException {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) throws ConnectionException {
        for (int i = 1; i <= maxAttempts; i++) {
            LOGGER.info("server connection");
            try (Connection connection = manager.getConnection()) {
                LOGGER.info("server connection is successful");
                connection.execute(command);
                LOGGER.info(command + " command was executed successfully");
                return;
            } catch (Exception e) {
                LOGGER.warn("command execution №" + i + " failed");
                LOGGER.warn(e.getCause());
                if (i == maxAttempts) {
                    throw new ConnectionException();
                }
            }
        }

    }
}
