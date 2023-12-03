package edu.hw8;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import edu.hw8.Task3.PasswordGenerator;
import org.junit.jupiter.api.Test;

public class Task3Test {

    @Test
    void test1() throws NoSuchAlgorithmException {
        System.out.println(new PasswordGenerator(Map.of("0cc175b9c0f1b6a831c399e269772661", "1")).getPasswordMap(2));
    }

    @Test
    void test() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];
        String st = "a";
        String st1 = "b";
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            // тут можно обработать ошибку
            // возникает она если в передаваемый алгоритм в getInstance(,,,) не существует
            e.printStackTrace();
        }
        BigInteger bigInt = new BigInteger(1, digest);

        String md5Hex = bigInt.toString(16);

        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }

        System.out.println(md5Hex);

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st1.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            // тут можно обработать ошибку
            // возникает она если в передаваемый алгоритм в getInstance(,,,) не существует
            e.printStackTrace();
        }
        bigInt = new BigInteger(1, digest);

        md5Hex = bigInt.toString(16);

        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }

        System.out.println(md5Hex);
    }
}
