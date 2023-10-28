package edu.hw3.Task6;

import java.util.PriorityQueue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StockMarketManager implements StockMarket {
    private final PriorityQueue<Stock> stockQueue = new PriorityQueue<>();

    @Override
    public void add(Stock stock) {

        log.info("adding a stock");
        stockQueue.add(stock);
        log.info("stock successfully added");
    }

    @Override
    public void remove(Stock stock) {
        log.info("removing a stock");
        stockQueue.remove(stock);
        log.info("stock successfully removed");
    }

    @Override
    public Stock mostValuableStock() {
        if (stockQueue.isEmpty()) {
            log.info("queue is empty");
            return null;
        }
        log.info("the most expensive stock received");
        return stockQueue.peek();
    }
}
