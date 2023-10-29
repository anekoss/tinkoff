package edu.hw3.Task2;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Task2 {
    private Task2() {

    }

    private static final char RIGHT_DELIMITER = ')';
    private static final char LEFT_DELIMITER = '(';

    public static List<String> clusterize(String str) {
        log.info("string clustering execution");
        if (str == null) {
            log.info("null string cannot be clustered");
            return List.of();
        }
        List<String> list = new ArrayList<>();
        int cnt = 0;
        int index = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == LEFT_DELIMITER) {
                cnt++;
            } else if (str.charAt(i) == RIGHT_DELIMITER) {
                cnt--;
                if (cnt == 0) {
                    list.add(str.substring(index, i + 1));
                }
            }
            if (cnt == 0) {
                index = i + 1;
            }
        }
        log.info("string clustering completed");
        return list;
    }
}
