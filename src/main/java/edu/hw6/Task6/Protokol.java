package edu.hw6.Task6;

public enum Protokol {
    UDP("UDP"), TCP("TCP"), UDP_TCP("UDP/TCP");
    private final String protokolName;

    Protokol(String protokolName) {
        this.protokolName = protokolName;
    }

    public String getProtokolName() {
        return protokolName;
    }

    public String getBusyStatus() {
        return "Занят";
    }

    public String getFreeStatus() {
        return "Свободен";
    }
}
