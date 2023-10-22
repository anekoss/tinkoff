package edu.hw2;

import edu.hw2.Task3.Connection;
import edu.hw2.Task3.ConnectionException;
import edu.hw2.Task3.DefaultConnectionManager;
import edu.hw2.Task3.FaultyConnectionManager;
import edu.hw2.Task3.PopularCommandExecutor;
import edu.hw2.Task3.StableConnection;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task3Test {

    @RepeatedTest(value = 100)
    void stableConnectionExecTest() throws Exception {
        try (Connection connection = new StableConnection()) {
            assertDoesNotThrow(() -> connection.execute("command"));
        }
    }

    @Test
    void faultyConnectionThrowExceptionTest() {
        while (true) {
            try (Connection connection = new FaultyConnectionManager().getConnection()) {
                connection.execute("command");
            } catch (Exception e) {
                assertTrue(true);
                break;
            }

        }
    }

    @Test
    void faultyConnectionSuccessfulExecuteTest() {
        while (true) {
            try (Connection connection = new FaultyConnectionManager().getConnection()) {
                connection.execute("command");
                assertTrue(true);
                break;
            } catch (Exception ignored) {

            }
        }
    }

    @ParameterizedTest
    @CsvSource({"0",
        "1",
        "2",
        "10"})
    void updatePackagesThrowExceptionTest() {
        while (true) {
            DefaultConnectionManager defaultConnectionManager = new DefaultConnectionManager();
            PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(defaultConnectionManager, 1);
            try {
                popularCommandExecutor.updatePackages();
            } catch (ConnectionException e) {
                assertThat(e.getCause()).isNull();
                break;
            }
        }
    }

    @ParameterizedTest
    @CsvSource({"0",
        "1",
        "2",
        "10"})
    void updatePackagesSuccessfulExecuteTest(int maxAttempts) {
        while (true) {
            DefaultConnectionManager defaultConnectionManager = new DefaultConnectionManager();
            PopularCommandExecutor popularCommandExecutor =
                new PopularCommandExecutor(defaultConnectionManager, maxAttempts);
            try {
                popularCommandExecutor.updatePackages();
                assertTrue(true);
                break;
            } catch (ConnectionException ignored) {
            }
        }
    }

    @ParameterizedTest
    @CsvSource({"1, 0.7"})
    void updatePackagesThrowExceptionTest1(int maxAttempts, double probability) {
        while (true) {
            DefaultConnectionManager defaultConnectionManager = new DefaultConnectionManager(probability);
            PopularCommandExecutor popularCommandExecutor =
                new PopularCommandExecutor(defaultConnectionManager, maxAttempts);
            try {
                popularCommandExecutor.updatePackages();
            } catch (ConnectionException e) {
                assertThat(e.getMessage()).isEqualTo("The maximum number of connection attempts has been reached");
                break;
            }
        }
    }

    @ParameterizedTest
    @CsvSource({"0, 0.7",
        "1, 0.4",
        "2, 0.2",
        "10, 0.1"})
    void updatePackagesSuccessfulExecuteTest1(int maxAttempts, double probability) {
        while (true) {
            DefaultConnectionManager defaultConnectionManager = new DefaultConnectionManager(probability);
            PopularCommandExecutor popularCommandExecutor =
                new PopularCommandExecutor(defaultConnectionManager, maxAttempts);
            try {
                popularCommandExecutor.updatePackages();
                assertTrue(true);
                break;
            } catch (ConnectionException ignored) {
            }
        }
    }

}

