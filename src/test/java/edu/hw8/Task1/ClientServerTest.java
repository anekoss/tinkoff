package edu.hw8.Task1;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class ClientServerTest {

    private static final Path PATH = Path.of("src/main/resources/h8/file.txt");
    private static final int MIN_COUNT_CONNECTIONS = 1;
    private static final int MAX_COUNT_CONNECTIONS = 4;

    @ParameterizedTest
    @CsvSource({"личности, 'Не переходи на личности там, где их нет'",
        "где, 'Не переходи на личности там, где их нет'",
        "'', Ты не хороший!"})
    public void testGetInvectiveSensitive(String word, String exceptedSensitive) throws IOException {
        new Thread(() -> {
            try {
                new Server(PATH, 9090, MIN_COUNT_CONNECTIONS).main();
            } catch (IOException ignored) {
            }
        }
        ).start();
        String invectiveSensitive =
            new Client(InetAddress.getLocalHost(), 9090).getInvectiveSensitive(word);
        assertThat(invectiveSensitive).isEqualTo(exceptedSensitive);
    }

    @Test
    public void testMinCountConnection() throws InterruptedException {
        int startCountThread = Thread.activeCount();
        new Thread(() -> {
            try {
                new Server(PATH, 7234, MIN_COUNT_CONNECTIONS).main();
            } catch (IOException ignored) {
            }
        }
        ).start();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                try {
                    new Client(InetAddress.getLocalHost(), 7234).getInvectiveSensitive(
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
        new Thread(() -> {
            try {
                new Server(PATH, 9190, 3193).main();
            } catch (IOException ignored) {
            }
        }
        ).start();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                try {
                    new Client(InetAddress.getLocalHost(), 3193).getInvectiveSensitive(
                        "личности");
                } catch (IOException ignored) {
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
