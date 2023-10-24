package edu.hw2.Task3;

public class DefaultConnectionManager implements ConnectionManager {
    public static final double DEFAULT_PROBABILITY_FAULTY_CONNECTION = 0.5;
    private double probability;

    public DefaultConnectionManager(double probability) {
        this.probability = probability;
    }

    public DefaultConnectionManager() {
        this.probability = DEFAULT_PROBABILITY_FAULTY_CONNECTION;
    }

    @Override
    public Connection getConnection() {
        if (Math.random() >= probability) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }
}
