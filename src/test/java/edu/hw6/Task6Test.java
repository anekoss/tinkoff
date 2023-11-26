package edu.hw6;

import edu.hw6.Task6.Protocol;
import edu.hw6.Task6.Task6;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import static edu.hw6.Task6.Protocol.TCP;
import static edu.hw6.Task6.Protocol.UDP;
import static edu.hw6.Task6.Protocol.UDP_TCP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class Task6Test {
    static Map<String, Map<Integer, String>> infoPorts;
    static Map<Integer, String> infoUdpPorts;
    static Map<Integer, String> infoTcpPorts;

    @BeforeAll
    static void init() {
        infoPorts = new Task6().scanPorts();
        infoTcpPorts = infoPorts.get("TCP");
        infoUdpPorts = infoPorts.get("UDP");
    }

    @Test
    void countScanInfoPortsTest() {
        assertThat(infoTcpPorts.size()).isGreaterThan(0);
        assertThat(infoUdpPorts.size()).isGreaterThan(0);
        assertThat(infoUdpPorts.size() + infoTcpPorts.size()).isEqualTo(98304);
    }

    @Test
    void udpPortInfoTest() {
        assertThat(new Task6().getInfoUdpPort(412)).isEqualTo( "UDP	412	SYNOPTICS-TRAP ");
    }

    @Test
    void tcpPortInfoTest() {
        assertThat(new Task6().getInfoTcpPort(81)).isEqualTo("TCP	81	HTTP");
    }

    @Test
    void countBusyPortMoreZeroTest() {
        Task6 task6 = Mockito.mock(Task6.class);
        when(task6.scanPorts()).thenCallRealMethod();
        when(task6.getInfoUdpPort(anyInt())).thenCallRealMethod();
        when(task6.getInfoTcpPort(anyInt())).thenCallRealMethod();
        task6.scanPorts();
        verify(task6, Mockito.atLeastOnce()).getBusyPortInfo(anyInt(), any(Protocol.class));
    }

    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(22, UDP_TCP, "UDP/TCP	22	SSH"),
            Arguments.of(81, TCP, "TCP	81	HTTP"),
            Arguments.of(412, UDP, "UDP	412	SYNOPTICS-TRAP "),
            Arguments.of(3325, UDP, "UDP	3325	Занят"),
            Arguments.of(3456, TCP, "TCP	3456	Занят")
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void correctBusyKnownPortInfoTest(int port, Protocol protocol, String info) {
        Task6 task6 = new Task6();
        assertThat(task6.getBusyPortInfo(port, protocol)).isEqualTo(info);
    }

}
