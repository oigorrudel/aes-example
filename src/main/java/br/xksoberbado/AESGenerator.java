package br.xksoberbado;

import lombok.SneakyThrows;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

public class AESGenerator {

    private static final String ALGORITHM = "AES";

    private static SecretKey secretKey;

    public static void main(String[] args) {
        System.out.println("Secret: " + Base64.getEncoder().encodeToString(getKey().getEncoded()));

        final var text = "Igor Rudel";
        System.out.println("Input: " + text);
        System.out.println("Encrypted: " + encrypt(text));
        System.out.println("Decrypted: " + decrypt(encrypt(text)));
    }

    @SneakyThrows
    private static String encrypt(final String text) {
        final var cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, getKey());

        final var bytes = cipher.doFinal(text.getBytes());

        return Base64.getEncoder().encodeToString(bytes);
    }

    @SneakyThrows
    private static String decrypt(final String text) {
        final var cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, getKey());

        final var bytes = cipher.doFinal(Base64.getDecoder().decode(text));

        return new String(bytes, StandardCharsets.UTF_8);
    }

    @SneakyThrows
    private static SecretKey getKey() {
        if (Objects.nonNull(secretKey)) {
            return secretKey;
        }

        final var keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(256);

        secretKey = keyGenerator.generateKey();

        return secretKey;
    }
}