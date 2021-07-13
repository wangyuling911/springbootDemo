package com.light.springboot.config;

import com.light.springboot.util.AESUtil;
import com.light.springboot.util.CommCryptUtil;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * jasypt.encryptor.password 这个参数需要作为VM变量放在启动命令中，放在配置文件中无效
 * jasypt.encryptor.firstKey 这个参数需要作为VM变量放在启动命令中，放在配置文件中无效
 * jasypt.encryptor.secondKey 这个参数需要作为VM变量放在启动命令中，放在配置文件中无效
 */

@Configuration
@Slf4j
public class EasemobEncryptConfig {
    private static final String thirdKey = "44E0ABD6C6AEF0D4DD74BB2AC82876E5E0A3D1DB776822205FDE29FCA2ACDB2734735038C36F145C7521AA77F787EF2D33913AD9B75DA701B2343A2D9B1E54EAD5C89D17D6F51F9D4F0D0F0D384CB2018C2B488371E1DACFD5ED4EEAF850BBB89579A09BD75E238CB1F8DDC41CA5B75B568FDB2E6C1104A4584202095E1A5DFD";
    private static final String salt = "D63226788EEBE1596F6DE2F2793D44A5";

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        //加密后的workKey，需要先用根密钥解密后使用
        String encryptWorkKey = System.getProperty("jasypt.encryptor.password");
        String firstKey = System.getProperty("jasypt.encryptor.firstKey");
        String secondKey = System.getProperty("jasypt.encryptor.secondKey");
        String password = decryptWorkKey(encryptWorkKey,firstKey,secondKey);
        return new EasemobJaspytEncrypt(password);
    }

    static class EasemobJaspytEncrypt implements StringEncryptor {
        private final String password;

        public EasemobJaspytEncrypt(String password) {
            this.password = password;
        }

        @Override
        public String encrypt(String content) {
            return AESUtil.encryptByAesCbc(content,password);
        }

        @Override
        public String decrypt(String content) {
            return AESUtil.decryptByAesCbc(content,password);
        }
    }

    private  byte[] getRootKey(String firstKey,String secondKey) {
        byte[] rootKey = null;
        try {
            rootKey = getRootKey(firstKey, secondKey, thirdKey, salt);
            CommCryptUtil.rootKey = rootKey;
        } catch (IllegalAccessException e) {
//            log.error("fali to get rootKey");//
            log.error("fali to get rootKey", e);//
//            e.printStackTrace();
        }
        return rootKey;
    }

    private static byte[] getRootKey(String first, String second, String third, String salt) throws IllegalAccessException {
        byte[] c1 = CommCryptUtil.hexStr2Byte(first);
        byte[] c2 = CommCryptUtil.hexStr2Byte(second);
        byte[] c3 = CommCryptUtil.hexStr2Byte(third);
        byte[] s = CommCryptUtil.hexStr2Byte(salt);
        int len = getMinValue(c1.length, c2.length, c3.length);
        if (!isKeyAndSaltValid(len, s)) {
            throw new IllegalAccessException("key length must be more than 128Bit.");
        } else {
            char[] combined = new char[len];
            for (int i = 0; i < len; ++i) {
                combined[i] = (char)(c1[i] ^ c2[i] ^ c3[i]);
            }
            return encryptBytePBKD2WithSHA256(combined, s, 10000, 16);
        }
    }

    private static int getMinValue(int len1, int len2, int len3) {
        int min = len1;
        if (len2 < len1) {
            min = len2;
        }
        if (len3 < min) {
            min = len3;
        }
        return min;
    }

    private static boolean isKeyAndSaltValid(int keyLength, byte[] salt) {
        boolean retVal1 = CommCryptUtil.isKeyLengthValid(keyLength);
        boolean retVal2 = CommCryptUtil.isKeyLengthValid(salt);
        return retVal1 & retVal2;
    }

    private static byte[] encryptBytePBKD2WithSHA256(char[] plainKey, byte[] salt, int iterationCount, int cipherLen) {
        PKCS5S2ParametersGenerator generator = new PKCS5S2ParametersGenerator(new SHA256Digest());
        generator.init(PKCS5S2ParametersGenerator.PKCS5PasswordToUTF8Bytes(plainKey), salt, iterationCount);
        KeyParameter key1 = (KeyParameter)generator.generateDerivedMacParameters(cipherLen * 8);
        return key1.getKey();
    }

    private static boolean plainWorkKeyIsHex(String plainWorkKey) {
        byte[] workKeyBytes = CommCryptUtil.hexStr2Byte(plainWorkKey);
        return workKeyBytes.length >= 16;
    }

    /**
     * 加密工作密钥
     *
     * @param plainWorkKey
     * @return
     */
    public String encryptWorkKey(String plainWorkKey,String firstKey,String secondKey) {
        return plainWorkKeyIsHex(plainWorkKey) ? AESUtil.encryptByAesCbc(plainWorkKey, getRootKey(firstKey,secondKey), CommCryptUtil.genSecureRandomByte(16)) : null;
    }

    /**
     * 解密工作密钥
     *
     * @param enWorkKey
     * @return
     */
    public String decryptWorkKey(String enWorkKey,String firstKey,String secondKey) {
        return AESUtil.decryptByAesCbc(enWorkKey, getRootKey(firstKey,secondKey));
    }
}
