package com.light.springboot.util;

import java.security.SecureRandom;

public class RandomUtils {
    public static String generateToken(int length) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[length];
        secureRandom.nextBytes(bytes);
        StringBuilder stringBuilder = new StringBuilder();
        String hv;
        //将生成的随机数转换成字符
        for (int i=0;i<bytes.length;i++){
            int v = bytes[i] & 0xFF; //转成16进制数
            hv = Integer.toHexString(v);//16进制转换成字符
            if (hv.length() == 1){
                hv = "0" + hv;
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
