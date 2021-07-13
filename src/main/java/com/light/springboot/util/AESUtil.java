package com.light.springboot.util;

import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/**
 * AES_CBC加密相关
 */

public class AESUtil {

    private static final Charset DEFAULT_CHARSET = Charset.forName("utf-8");

    public static String encryptByAesCbc(String plainText, String workKey) {
        if (StringUtils.isBlank(plainText) || StringUtils.isBlank(workKey)) {
            return plainText;
        }
        return encryptByAesCbc(plainText, CommCryptUtil.hexStr2Byte(workKey), CommCryptUtil.genSecureRandomByte(16));
    }

    public static void main(String[] args) {

    }

    public static String encryptByAesCbc(String plainKey, byte[] secret, byte[] ivBytes) {
        if (plainKey == null || secret == null || ivBytes == null) {
            return null;
        } else {
            if (!CommCryptUtil.isKeyLengthValid(secret)) {
                throw new IllegalArgumentException("key length must be more than 128bit");
            } else {
                byte[] keyBytes = Arrays.copyOf(secret, 16);
                byte[] cipherKey = aes(plainKey.getBytes(DEFAULT_CHARSET), keyBytes, Arrays.copyOf(ivBytes, 16), Cipher.ENCRYPT_MODE);
                return  "security:" + CommCryptUtil.byte2HexStr(ivBytes) + ":" + CommCryptUtil.byte2HexStr(cipherKey);
            }
        }
    }

    public static String decryptByAesCbc(String plainText, String workKey) {
        if (StringUtils.isBlank(plainText) || StringUtils.isBlank(workKey)) {
            return plainText;
        }
        return decryptByAesCbc(plainText, CommCryptUtil.hexStr2Byte(workKey));
    }

    public static String decryptByAesCbc(String encryptText, byte[] key) {
        if (encryptText == null || key == null) {
            return encryptText;
        } else {
            String subEncryptText = CommCryptUtil.stripCryptHead(encryptText);
            if (subEncryptText == null) {
                return encryptText;
            } else {
                int index = subEncryptText.indexOf(58);
                if (index >= 0) {
                    byte[] ivBytes = CommCryptUtil.hexStr2Byte(subEncryptText.substring(0, index));
                    byte[] cipherKey = CommCryptUtil.hexStr2Byte(subEncryptText.substring(index + 1));
                    byte[] plainKey = aes(cipherKey, Arrays.copyOf(key, 16), Arrays.copyOf(ivBytes, 16), Cipher.DECRYPT_MODE);
                    return new String(plainKey, DEFAULT_CHARSET);
                } else {
                    throw new IllegalArgumentException("missing colon");
                }
            }
        }
    }

    /**
     * 使用AES加密或解密无编码的原始字节数组, 返回无编码的字节数组结果.
     *
     * @param input 原始字节数组
     * @param key 符合AES要求的密钥
     * @param iv 初始向量
     * @param mode Cipher.ENCRYPT_MODE 或 Cipher.DECRYPT_MODE
     * @return
     */
    private static byte[] aes(byte[] input, byte[] key, byte[] iv, int mode) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(mode, secretKey, ivSpec);
            return cipher.doFinal(input);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return input;
    }


}
