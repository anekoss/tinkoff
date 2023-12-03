package edu.hw8;

import edu.hw8.Task1.Client;
import edu.hw8.Task1.Server;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

public class Task1Test {

    @Test
    void test() throws IOException, InterruptedException, ClassNotFoundException {
        Server server = new Server(Path.of("src/main/resources/h8/file.json"), 4004, 1);
        Thread thread1 = new Thread(() -> {
            try {
                server.main();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        thread1.start();

        Client client = new Client(InetAddress.getLocalHost(), 4004);
        System.out.println(client.getInvectiveSensitive(""));
        client.getInvectiveSensitive("hiiii");
        System.out.println("mew");
    }
}
