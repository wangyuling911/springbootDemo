package com.light.springboot.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class CommCryptUtil {

    public static final int KEY_128_SIZE = 128;
    public static byte[] rootKey = null;

    public static String byte2HexStr(byte[] array) {
        return array == null ? null : new String(Hex.encodeHex(array, false));
    }

//    public static void main(String[] args) throws DecoderException {
//
////        char a='王';
////        String s1 = Integer.toBinaryString(a);
////        System.out.println(s1);
////
////        //String s = Byte.toString(a);
////        String s3 = Integer.toString((int) a, 10);
////        System.out.println(s3);
////        String s2 = Integer.toBinaryString(Integer.valueOf(s3));
////        System.out.println(s2);
//        byte[] a=new byte[]{'A'};
//        char[] chars = Hex.encodeHex(a);
//        byte b='A';
//        String s1 = Integer.toString((int) b, 16);
//        System.out.println(s1);
//        for (char c : chars) {
//            System.out.println(c);
//        }
//        String s = String.valueOf(chars);
//        System.out.println(s);
//
//    }

    public static byte[] hexStr2Byte(String hexStr) {
        try {
            return hexStr == null ? new byte[0] : Hex.decodeHex(hexStr.toCharArray());
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }


    public static String stripCryptHead(String encryptText) {
        int index = encryptText.indexOf("security:");
        return index == -1 ? null : encryptText.substring("security:".length());
    }

    public static boolean isKeyLengthValid(byte[] keyByte) {
        return keyByte.length >= 16;
    }

    public static boolean isKeyLengthValid(int length) {
        return length >= 16;
    }

    public static byte[] genSecureRandomByte(int size) {
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[size];
        if (sr != null) {
            sr.nextBytes(bytes);
        }
        return bytes;
    }

    public static void main(String[] args) {
        byte[] bytes = CommCryptUtil.genSecureRandomByte(10);
        for (byte b : bytes) {
            System.out.println(b);
        }
        System.out.println();
    }



    public static byte[] getSaltNum() {
        return genSecureRandomByte(16);
    }


    /**
     * 解密工作密钥
     *
     * @param enWorkKey
     * @return
     */
    public static String decryptWorkKey(String enWorkKey) {
        return AESUtil.decryptByAesCbc(enWorkKey, rootKey);
    }
}
