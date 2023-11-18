package edu.hw6.Task6;

import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Map;
import java.util.TreeMap;
import static edu.hw6.Task6.Protocol.TCP;
import static edu.hw6.Task6.Protocol.UDP;

public class Task6 {
    private final Map<Integer, Map.Entry<Protocol, String>> knownPorts = new TreeMap<>();
    private final PrintStream printStream;
    private final static int END_PORT = 49151;
    private final static int START_PORT = 0;
    private final static String SEPARATOR = "\t";
    private final static int PORT_22 = 22;
    private final static int PORT_8080 = 8080;
    private final static int PORT_135 = 135;
    private final static int PORT_53 = 53;
    private final static int PORT_81 = 81;
    private final static int PORT_412 = 412;

    {
        knownPorts.put(PORT_22, Map.entry(Protocol.UDP_TCP, "SSH"));
        knownPorts.put(PORT_8080, Map.entry(TCP, "Альтернативный порт HTTP (http_alt)"));
        knownPorts.put(PORT_135, Map.entry(Protocol.UDP_TCP, "EPMAP"));
        knownPorts.put(PORT_53, Map.entry(Protocol.UDP_TCP, "DOMAIN "));
        knownPorts.put(PORT_81, Map.entry(TCP, "HTTP"));
        knownPorts.put(PORT_412, Map.entry(UDP, "SYNOPTICS-TRAP "));
    }

    public Task6(PrintStream printStream) {
        this.printStream = printStream;
    }

    public Task6() {
        this.printStream = System.out;
    }

    public void scanPorts() {
        for (int port = START_PORT; port <= END_PORT; port++) {
            try (
                ServerSocket ignored = new ServerSocket(port)) {
                printStream.println(TCP + SEPARATOR + port + SEPARATOR + TCP.getFreeStatus());
            } catch (IOException e) {
                printStream.println(getBusyPortInfo(port, TCP));
            }
            try (
                DatagramSocket ignored = new DatagramSocket(port)) {
                printStream.println(UDP + SEPARATOR + port + SEPARATOR + UDP.getFreeStatus());
            } catch (IOException e) {
                printStream.println(getBusyPortInfo(port, UDP));
            }
        }
    }

    public String getBusyPortInfo(int port, Protocol protocol) {
        if (knownPorts.containsKey(port)) {
            Map.Entry<Protocol, String> portInfo = knownPorts.get(port);
            if (portInfo.getKey() == protocol
                || portInfo.getKey() == Protocol.UDP_TCP) {
                return protocol.getProtocolName() + SEPARATOR + port + SEPARATOR + portInfo.getValue();
            }
        }
        return protocol + SEPARATOR + port + SEPARATOR + protocol.getBusyStatus();
    }

    public Map<Integer, Map.Entry<Protocol, String>> getKnownPorts() {
        return knownPorts;
    }
}
