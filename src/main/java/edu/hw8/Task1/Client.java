package edu.hw8.Task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class Client {
    private final InetAddress host;
    private final int serverPort;
    private static final String DEFAULT_WORD = "\n";

    public Client(@NotNull InetAddress host, int serverPort) {
        this.host = host;
        this.serverPort = serverPort;
    }

    public String getInvectiveSensitive(@NotNull String word) throws IOException {
        if (word.isEmpty()) {
            word = DEFAULT_WORD;
        }
        try (Socket socket = new Socket(host, serverPort);
             OutputStream outputStream = socket.getOutputStream();
             InputStream inputStream = socket.getInputStream()) {
            sendMessage(word, outputStream);
            return getResponse(inputStream);
        }
    }

    private void sendMessage(String word, OutputStream outputStream) throws IOException {
        try {
            outputStream.write(word.getBytes());
        } catch (IOException e) {
            log.error("error sending a word to the server");
            throw e;
        }
    }

    private String getResponse(InputStream inputStream) throws IOException {
        StringBuilder sensitive = new StringBuilder();
        byte[] buffer = new byte[1024];
        int bytesRead;
        try {

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                sensitive.append(new String(buffer, 0, bytesRead));
            }
            return sensitive.toString();
        } catch (IOException e) {
            log.error("error receiving a invective sensitive from the server");
            throw e;
        }

    }
}
