package edu.hw3.Task6;

import org.jetbrains.annotations.NotNull;

public class Stock implements Comparable<Stock> {
    private final int marketPrice;
    private final String stockName;

    public Stock(String stockName, int marketPrice) {

        this.stockName = stockName;
        this.marketPrice = marketPrice;
    }

    @Override
    public int compareTo(@NotNull Stock o) {
        int delta = this.marketPrice - o.marketPrice;
        if (delta != 0) {
            return -delta;
        }
        return this.stockName.compareTo(o.stockName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Stock stock = (Stock) o;
        return marketPrice == stock.marketPrice && stockName.equals(stock.stockName);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
