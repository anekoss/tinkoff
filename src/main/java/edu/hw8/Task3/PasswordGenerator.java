package edu.hw8.Task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import static edu.hw8.Task3.PasswordIterator.nextPassword;

@Slf4j
public class PasswordGenerator {
    private static final String STRING_FORMAT = "%02x";
    private static final int MAX_CODE = 0xff;

    public static final char[] SYMBOLS =
        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
    private final Map<String, String> hashPasswordMap;
    private final int threadIteration = 100000;
    private final AtomicInteger passwordLength = new AtomicInteger(0);

    public PasswordGenerator(Map<String, String> hashPasswordMap) {
        this.hashPasswordMap = hashPasswordMap;
    }

    public Map<String, String> getPasswordMap(int countThreads) {
        if (countThreads <= 0) {
            return Map.of();
        }
        Map<String, String> passwordMap = new HashMap<>();
        Thread[] threads = new Thread[countThreads];
        for (int i = 0; i < countThreads; i++) {
            Thread thread = new Thread(() -> {
                while (passwordMap.size() != hashPasswordMap.size()) {
                    try {
                        addPasswordIfExist(passwordLength.addAndGet(threadIteration), passwordMap);
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            threads[i] = thread;
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("interrupted thread exception");
            }
        }
        return passwordMap;
    }

    public String generateMD5Hash(@NotNull String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest1 = MessageDigest.getInstance("MD5");
        messageDigest1.update(password.getBytes());
        StringBuilder md5Hex = new StringBuilder();
        for (byte b : messageDigest1.digest()) {
            md5Hex.append(String.format(STRING_FORMAT, b & MAX_CODE));
        }
        return md5Hex.toString();
    }

    private void addPasswordIfExist(int numberPassword, Map<String, String> passwordMap)
        throws NoSuchAlgorithmException {
        for (int i = numberPassword - threadIteration; i < numberPassword; i++) {
            String code = nextPassword(i, SYMBOLS);
            String md5Hash = generateMD5Hash(code);
            if (hashPasswordMap.containsKey(md5Hash)) {
                passwordMap.put(code, hashPasswordMap.get(md5Hash));
            }
        }
    }

}
