package edu.hw7;

import edu.hw7.Task3.CashedPersonDatabase;
import edu.hw7.Task3.Person;
import org.junit.jupiter.api.Test;

public class PersonDatabaseTest {
    @Test
    void test() throws InterruptedException {
        CashedPersonDatabase cashedPersonDatabase = new CashedPersonDatabase();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                cashedPersonDatabase.add(new Person(1, "h", "ff", "333"));
            });
//            Thread thread1 = new Thread(() -> {
//                System.out.println(cashedPersonDatabase.findByName("h"));
//            });
//            Thread thread2 = new Thread(() -> {
//                System.out.println(cashedPersonDatabase.findByAddress("ff"));
//            });
//            Thread thread3 = new Thread(() -> {
//                System.out.println(cashedPersonDatabase.findByPhone("333"));
//            });
            thread.start();
            thread.join();
            System.out.println(cashedPersonDatabase.findByAddress("ff"));
//            thread1.start();
//            thread2.start();
//            thread3.start();
//            thread1.join();
//            thread2.join();
//            thread3.join();
//            thread.join();
        }
    }
}
