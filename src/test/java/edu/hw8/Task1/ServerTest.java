package edu.hw8.Task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ServerTest {

    private ServerSocket serverSocket;

    private InvectiveService invectiveService;

    private ExecutorService executorService;

    private Socket socket;

    @BeforeEach
    void setUp() throws Exception {
        socket = mock(Socket.class);
        invectiveService = mock(InvectiveService.class);
        serverSocket = mock(ServerSocket.class);
        executorService = mock(ExecutorService.class);
    }

    @Test
    void testMain() throws IOException {
        AtomicInteger acceptCount = new AtomicInteger(0);
        Server server = new Server(serverSocket, invectiveService, executorService);
        doAnswer(invocation -> {
            if (acceptCount.getAndIncrement() == 0) {
                return socket;
            } else {
                throw new IOException();
            }
        }).when(serverSocket).accept();
        assertThrows(IOException.class, server::main);
        verify(serverSocket, times(2)).accept();
    }
}
