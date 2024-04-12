package edu.hw8.Task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientHandlerServer implements Runnable {
    private final Socket socket;
    private final InputStream in;
    private final static int MAX_BUFFER_SIZE = 1024;
    private final OutputStream out;
    private final InvectiveService invectiveService;

    public ClientHandlerServer(Socket socket, InvectiveService invectiveService) throws IOException {
        this.socket = socket;
        in = socket.getInputStream();
        out = socket.getOutputStream();
        this.invectiveService = invectiveService;
    }

    @SneakyThrows @Override
    public void run() {
        try {
            String word = getMessage();
            String response = invectiveService.getInvectiveSensitive(word);
            sendResponse(response);
        } finally {
            in.close();
            out.close();
            socket.close();
        }

    }

    private void sendResponse(String sensitive) throws IOException {
        try {
            out.write(sensitive.getBytes());
        } catch (IOException e) {
            log.error("error receiving a word from a client");
            throw e;
        }
    }

    private String getMessage() throws IOException {
        byte[] buffer = new byte[MAX_BUFFER_SIZE];
        try {
            int bytesRead = in.read(buffer);
            return new String(buffer, 0, bytesRead);
        } catch (IOException e) {
            log.error("error sending a invective sensitive to the client");
            throw e;
        }
    }

}
