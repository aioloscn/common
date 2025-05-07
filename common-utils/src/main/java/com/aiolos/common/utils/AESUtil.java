package com.aiolos.common.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 更安全的加密工具
 */
public class AESUtil {

    /**
     * AES加密（CBC模式，自动生成IV）
     * @param key 密钥，长度必须为16/24/32字节（对应128/192/256位）
     * @param src 明文
     * @return Base64编码的字符串，格式：IV向量(16字节) + 密文
     */
    public static String encrypt(String key, String src) {
        try {
            // 检查密钥长度
            validateKeyLength(key.getBytes());

            // 生成随机IV（16字节）
            byte[] ivBytes = new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(ivBytes);
            IvParameterSpec iv = new IvParameterSpec(ivBytes);

            // 构建密钥和密码器
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

            // 执行加密
            byte[] encryptedBytes = cipher.doFinal(src.getBytes(StandardCharsets.UTF_8));

            // 合并IV和密文
            byte[] combined = new byte[ivBytes.length + encryptedBytes.length];
            System.arraycopy(ivBytes, 0, combined, 0, ivBytes.length);
            System.arraycopy(encryptedBytes, 0, combined, ivBytes.length, encryptedBytes.length);

            return Base64.getEncoder().encodeToString(combined);
        } catch (Exception e) {
            throw new RuntimeException("AES加密失败", e);
        }
    }

    /**
     * AES解密
     * @param key 密钥，必须与加密时一致
     * @param encryptedText Base64格式的加密字符串（IV+密文）
     * @return 明文
     */
    public static String decrypt(String key, String encryptedText) {
        try {
            // 检查密钥长度
            validateKeyLength(key.getBytes());

            // Base64解码
            byte[] combined = Base64.getDecoder().decode(encryptedText);

            // 分离IV（前16字节）和密文
            byte[] ivBytes = new byte[16];
            byte[] encryptedBytes = new byte[combined.length - ivBytes.length];
            System.arraycopy(combined, 0, ivBytes, 0, ivBytes.length);
            System.arraycopy(combined, ivBytes.length, encryptedBytes, 0, encryptedBytes.length);

            // 初始化解密器
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(ivBytes));

            // 执行解密
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("AES解密失败", e);
        }
    }

    /**
     * 校验密钥长度有效性
     * @param keyBytes 密钥字节数组
     */
    private static void validateKeyLength(byte[] keyBytes) {
        int length = keyBytes.length;
        if (length != 16 && length != 24 && length != 32) {
            throw new IllegalArgumentException("无效的AES密钥长度（必须为16/24/32字节）");
        }
    }

    // 测试用例
    public static void main(String[] args) {
        String key = "1234567890123456"; // 16字节密钥
        String phone = "13812345678";

        String encrypted = encrypt(key, phone);
        System.out.println("加密结果：" + encrypted);

        String decrypted = decrypt(key, encrypted);
        System.out.println("解密结果：" + decrypted);
    }
}
