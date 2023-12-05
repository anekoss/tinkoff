package edu.hw8.Task1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class ClientServerTest {

    private static final Path PATH = Path.of("src/main/resources/h8/file.txt");

    @ParameterizedTest
    @CsvSource({"личности, 'Не переходи на личности там, где их нет'",
        "где, 'Не переходи на личности там, где их нет'",
        "'', Ты не хороший!"})
    public void getInvectiveSensitiveTest(String word, String exceptedSensitive) throws InterruptedException {
        int port = new InetSocketAddress(0).getPort();
        Thread thread = new Thread(() -> {
            try {
                new Server(PATH, port, 1).main();
            } catch (IOException ignored) {
            }
        }
        );
        thread.start();
        Thread.sleep(5000);
        try {
            String invectiveSensitive = new Client(InetAddress.getLocalHost(), port).getInvectiveSensitive(word);
            assertThat(invectiveSensitive).isEqualTo(exceptedSensitive);
        } catch (IOException ignored) {
        }
    }

    @Test
    public void minCountConnectionTest() throws InterruptedException {
        new Thread(() -> {
            try {
                new Server(PATH, 7234, 1).main();
            } catch (IOException ignored) {
            }
        }
        ).start();
        int startCountThread = Thread.activeCount();
        Thread.sleep(5000);
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
            assertThat(Thread.activeCount() - startCountThread).isLessThanOrEqualTo(1 + i + 1);
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }

    @Test
    public void maxCountConnectionTest() throws InterruptedException {
        new Thread(() -> {
            try {
                new Server(PATH, 9190, 4).main();
            } catch (IOException ignored) {
            }
        }
        ).start();
        int startCountThread = Thread.activeCount();
        Thread.sleep(5000);
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
            assertThat(Thread.activeCount() - startCountThread).isLessThanOrEqualTo(4 + i + 1);
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }

}
