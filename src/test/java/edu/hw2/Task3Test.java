package edu.hw2;

import edu.hw2.Task3.Connection;
import edu.hw2.Task3.ConnectionException;
import edu.hw2.Task3.DefaultConnectionManager;
import edu.hw2.Task3.FaultyConnectionManager;
import edu.hw2.Task3.PopularCommandExecutor;
import edu.hw2.Task3.StableConnection;
import io.github.artsok.RepeatedIfExceptionsTest;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task3Test {

    @RepeatedTest(value = 100)
    public void stableConnectionExecTest() throws Exception {
        try (Connection connection = new StableConnection()) {
            assertDoesNotThrow(() -> connection.execute("command"));
        }
    }

    @RepeatedIfExceptionsTest(repeats = 100, suspend = 100)
    public void stableConnectionExecTest2() throws Exception {
        try (Connection connection = new StableConnection()) {
            assertDoesNotThrow(() -> connection.execute("command"));
        }
    }

    @Test
    public void faultyConnectionThrowExceptionTest() {
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
    public void faultyConnectionSuccessfulExecuteTest() {
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
    public void updatePackagesThrowExceptionTest() {
        while (true) {
            DefaultConnectionManager defaultConnectionManager = new DefaultConnectionManager();
            PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(defaultConnectionManager, 1);
            try {
                popularCommandExecutor.updatePackages();
            } catch (ConnectionException e) {
                assertThat(e.getCause()).isEqualTo(null);
                break;
            }
        }
    }

    @ParameterizedTest
    @CsvSource({"0",
        "1",
        "2",
        "10"})
    public void updatePackagesSuccessfulExecuteTest(int maxAttempts) {
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
}

