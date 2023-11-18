package edu.hw6;

import edu.hw6.Task6.Protocol;
import edu.hw6.Task6.Task6;
import java.io.PrintStream;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static edu.hw6.Task6.Protocol.TCP;
import static edu.hw6.Task6.Protocol.UDP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

public class Task6Test {

    @Test
    void countPrintScanPortsTest() {
        PrintStream printStream = Mockito.mock(PrintStream.class);
        Task6 task6 = new Task6(printStream);
        task6.scanPorts();
        verify(printStream, Mockito.times(49152 * 2)).println(anyString());
    }

    @Test
    void countBusyPortMoreZeroTest() {
        Task6 task6 = Mockito.spy(Task6.class);
        task6.scanPorts();
        verify(task6, Mockito.atLeastOnce()).getBusyPortInfo(anyInt(), any(Protocol.class));
    }

    @Test
    void correctBusyKnownPortInfoTest() {
        Task6 task6 = new Task6();
        for (Map.Entry<Integer, Map.Entry<Protocol, String>> entry : task6.getKnownPorts().entrySet()) {
            if (entry.getValue().getKey() == UDP) {
                assertThat(task6.getBusyPortInfo(entry.getKey(), UDP)).isEqualTo(
                    "UDP\t" + entry.getKey() + "\t" + entry.getValue().getValue());
                assertThat(task6.getBusyPortInfo(entry.getKey(), TCP)).isEqualTo("TCP\t" + entry.getKey() + "\tЗанят");
            } else if (entry.getValue().getKey() == TCP) {
                assertThat(task6.getBusyPortInfo(entry.getKey(), TCP)).isEqualTo(
                    "TCP\t" + entry.getKey() + "\t" + entry.getValue().getValue());
                assertThat(task6.getBusyPortInfo(entry.getKey(), UDP)).isEqualTo("UDP\t" + entry.getKey() + "\tЗанят");
            } else {
                assertThat(task6.getBusyPortInfo(entry.getKey(), UDP)).isEqualTo(
                    "UDP\t" + entry.getKey() + "\t" + entry.getValue().getValue());
                assertThat(task6.getBusyPortInfo(entry.getKey(), TCP)).isEqualTo(
                    "TCP\t" + entry.getKey() + "\t" + entry.getValue().getValue());
            }
        }
    }

}
