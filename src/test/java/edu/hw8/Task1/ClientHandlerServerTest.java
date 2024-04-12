package edu.hw8.Task1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ClientHandlerServerTest {

    private InvectiveService invectiveService;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    @BeforeEach
    void setUp() throws Exception {
        socket = Mockito.mock(Socket.class);
        invectiveService = new InvectiveService(Path.of("src/main/resources/hw8/file.txt"));
        outputStream = new ByteArrayOutputStream();
        when(socket.getOutputStream()).thenReturn(outputStream);
    }

    @Test
    void clientHandlerServerNotWordTest() throws Exception {
        inputStream = new ByteArrayInputStream("test".getBytes());
        when(socket.getInputStream()).thenReturn(inputStream);
        ClientHandlerServer clientHandlerServer = new ClientHandlerServer(socket, invectiveService);
        clientHandlerServer.run();
        assertThat(outputStream.toString()).isEqualTo("Ты не хороший!");
    }

    @Test
    void clientHandlerServerHaveWordTest() throws Exception {
        inputStream = new ByteArrayInputStream("личности".getBytes());
        when(socket.getInputStream()).thenReturn(inputStream);
        ClientHandlerServer clientHandlerServer = new ClientHandlerServer(socket, invectiveService);
        clientHandlerServer.run();
        assertThat(outputStream.toString()).isEqualTo("Не переходи на личности там, где их нет");
    }
}
