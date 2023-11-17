package com.example.demo.crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class QueryStringEncryption {
    private static final String AES_ALGORITHM = "AES";
    private static final String SECRET_KEY = "YourSecretKey123"; // 16자 이상의 키 사용

    public static String encrypt(String input) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), AES_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), AES_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedBytes = Base64.getDecoder().decode(encrypted);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            // 원본 쿼리스트링
            String originalQueryString = "/api/newFeed/list?userMail=lhg@naver.com";

            // 쿼리스트링 암호화
            String encryptedQueryString = encrypt(originalQueryString);
            System.out.println("Encrypted QueryString: " + URLEncoder.encode(encryptedQueryString, "UTF-8"));

            // 암호화된 쿼리스트링 디코딩 및 복호화
            String decodedQueryString = URLDecoder.decode(encryptedQueryString, "UTF-8");
            String decryptedQueryString = decrypt(decodedQueryString);
            System.out.println("Decrypted QueryString: " + decryptedQueryString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
