package edu.hw8.Task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final ServerSocket serverSocket;
    private final InvectiveService invectiveServiceGenerator;
    private final ExecutorService executorService;

    public Server(Path path, int port, int maxConnections) throws IOException {
        this.invectiveServiceGenerator = new InvectiveService(path);
        this.serverSocket = new ServerSocket(port);
        this.executorService = Executors.newFixedThreadPool(maxConnections);
    }

    public void main() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            executorService.submit(new ClientHandlerServer(socket, invectiveServiceGenerator));
        }

    }
}
