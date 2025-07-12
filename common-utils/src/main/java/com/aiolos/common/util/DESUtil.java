package com.aiolos.common.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class DESUtil {

    /**
     * 加密
     * @param key 最少8位
     * @param src
     * @return
     */
    private static String encrypt(String key, String src) {
        byte[] token = new byte[0];
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secureKey = keyFactory.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secureKey, random);
            token = cipher.doFinal(src.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(token);
    }

    /**
     * 解密
     * @param key 最少8位
     * @param password
     * @return
     */
    private static String decrypt(String key, String password) {
        byte[] src = new byte[0];
        try {
            byte[] buf = Base64.getDecoder().decode(password);
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secureKey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, secureKey, random);
            src = cipher.doFinal(buf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(src);
    }

    public static void main(String[] args) {
        String key = "aiolos123";
        String encrypt = encrypt(key, "123456");
        System.out.println(encrypt);
        System.out.println(decrypt(key, encrypt));
    }
}
