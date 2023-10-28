package edu.hw3;

import edu.hw3.Task6.Stock;
import edu.hw3.Task6.StockMarketManager;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

class Task6Test {

    static Stream<Arguments> provideDataForAddTest() {
        return Stream.of(
            Arguments.of(List.of(new Stock("a", 100), new Stock("b", 200), new Stock("c", 50)), new Stock("b", 200)),
            Arguments.of(List.of(new Stock("a", 100), new Stock("b", 100), new Stock("c", 50)), new Stock("a", 100)),
            Arguments.of(List.of(new Stock("a", 100), new Stock("a", 200), new Stock("c", 50)), new Stock("a", 200))

        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForAddTest")
    void stockManagerAddTest(List<Stock> list, Stock excepted) {
        StockMarketManager stockMarketManager = new StockMarketManager();
        for (Stock stock : list) {
            stockMarketManager.add(stock);
        }
        assertThat(stockMarketManager.mostValuableStock()).isEqualTo(excepted);
    }

    static Stream<Arguments> provideDataForRemoveTest() {
        return Stream.of(
            Arguments.of(
                List.of(new Stock("a", 100), new Stock("b", 200), new Stock("c", 50)),
                new Stock("b", 200),
                new Stock("a", 100)
            ),
            Arguments.of(
                List.of(new Stock("a", 100), new Stock("b", 100), new Stock("c", 50)),
                new Stock("a", 100),
                new Stock("b", 100)
            ),
            Arguments.of(
                List.of(new Stock("a", 100), new Stock("a", 200), new Stock("c", 50)),
                new Stock("a", 200),
                new Stock("a", 100)
            )

        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForRemoveTest")
    void stockManagerRemoveTest(List<Stock> list, Stock removeStock, Stock excepted) {
        StockMarketManager stockMarketManager = new StockMarketManager();
        for (Stock stock : list) {
            stockMarketManager.add(stock);
        }
        stockMarketManager.remove(removeStock);
        assertThat(stockMarketManager.mostValuableStock()).isEqualTo(excepted);
    }

}
