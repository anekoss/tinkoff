package edu.hw2.Task3;

public class DefaultConnectionManager implements ConnectionManager {
    public static final double DEFAULT_PROBABILITY_FAULTY_CONNECTION = 0.5;

    public DefaultConnectionManager() {

    }


    @Override
    public Connection getConnection() {
        if (Math.random() >= DEFAULT_PROBABILITY_FAULTY_CONNECTION) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
