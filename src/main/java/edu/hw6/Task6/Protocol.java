package edu.hw6.Task6;

public enum Protocol {
    UDP("UDP"), TCP("TCP"), UDP_TCP("UDP/TCP");
    private final String protocolName;
    Protocol(String protocolName) {
        this.protocolName = protocolName;
    }

    public String getProtocolName() {
        return protocolName;
    }

    public String getBusyStatus() {
        return "Занят";
    }

    public String getFreeStatus() {
        return "Свободен";
    }
}
