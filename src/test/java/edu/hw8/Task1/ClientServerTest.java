package edu.hw8.Task1;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class ClientServerTest {

    private static final Path PATH = Path.of("src/main/resources/h8/file.txt");
    private static final int MIN_CONNECTION_SERVER_PORT = 7777;
    private static final int MIN_COUNT_CONNECTIONS = 1;
    private static final int MAX_CONNECTION_SERVER_PORT = 9090;
    private static final int MAX_COUNT_CONNECTIONS = 4;
    private static Server minConnectionServer;
    private static Server maxConnectionServer;

    @BeforeAll
    static void setUpServer() throws IOException {
        minConnectionServer = new Server(PATH, MIN_CONNECTION_SERVER_PORT, MIN_COUNT_CONNECTIONS);
        maxConnectionServer = new Server(PATH, MAX_CONNECTION_SERVER_PORT, MAX_COUNT_CONNECTIONS);
    }

    @BeforeEach
    public void setUp() {
        new Thread(() -> {
            try {
                minConnectionServer.main();
                maxConnectionServer.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ).start();
    }

    @ParameterizedTest
    @CsvSource({"личности, 'Не переходи на личности там, где их нет'",
        "где, 'Не переходи на личности там, где их нет'",
        "'', Ты не хороший!"})
    public void testGetInvectiveSensitive(String word, String exceptedSensitive) throws IOException {

        String invectiveSensitive =
            new Client(InetAddress.getLocalHost(), MAX_CONNECTION_SERVER_PORT).getInvectiveSensitive(word);
        assertThat(invectiveSensitive).isEqualTo(exceptedSensitive);
    }

    @Test
    public void testMinCountConnection() throws InterruptedException {
        int startCountThread = Thread.activeCount();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                try {
                    new Client(InetAddress.getLocalHost(), MIN_CONNECTION_SERVER_PORT).getInvectiveSensitive(
                        "личности");
                } catch (IOException ignored) {
                }
            });
            thread.start();
            threads[i] = thread;
            assertThat(Thread.activeCount() - startCountThread).isLessThanOrEqualTo(MIN_COUNT_CONNECTIONS + i + 1);
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }

    @Test
    public void testMaxCountConnection() throws InterruptedException {
        int startCountThread = Thread.activeCount();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                try {
                    new Client(InetAddress.getLocalHost(), MAX_CONNECTION_SERVER_PORT).getInvectiveSensitive(
                        "личности");
                } catch (IOException e) {
                    System.out.println("new");
                }
            });
            thread.start();
            threads[i] = thread;
            assertThat(Thread.activeCount() - startCountThread).isLessThanOrEqualTo(MAX_COUNT_CONNECTIONS + i + 1);
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }
}
