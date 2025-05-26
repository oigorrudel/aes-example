package br.xksoberbado;

import lombok.SneakyThrows;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

public class AESTester {

    private static final String ALGORITHM = "AES";
    private static final String KEY = "00Blhtt/HsNZR54A0NvXheSuWpX31uw5jQSssbWlvGk=";

    private static SecretKey secretKey;

    public static void main(String[] args) {
        System.out.println("Secret: " + KEY);

        final var text = "Igor Rudel";
        System.out.println("Input: " + text);
        System.out.println("Encrypted: " + encrypt(text));
        System.out.println("Decrypted: " + decrypt(encrypt(text)));
    }

    @SneakyThrows
    private static String encrypt(final String text) {
        final var cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, getKey());

        final var bytes = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(bytes);
    }

    @SneakyThrows
    private static String decrypt(final String text) {
        final var cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, getKey());

        final var bytes = cipher.doFinal(Base64.getDecoder().decode(text.getBytes()));

        return new String(bytes, StandardCharsets.UTF_8);
    }

    @SneakyThrows
    private static SecretKey getKey() {
        if (Objects.nonNull(secretKey)) {
            return secretKey;
        }

        final var key = Base64.getDecoder().decode(KEY);
        secretKey = new SecretKeySpec(key, 0, key.length, ALGORITHM);

        return secretKey;
    }
}
