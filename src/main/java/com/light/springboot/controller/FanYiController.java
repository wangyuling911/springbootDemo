package com.light.springboot.controller;

import com.light.springboot.util.Sender;

public class FanYiController {


    public static void main(String[] args) {
        String serviceURL = "http://mt.cn-hangzhou.aliyuncs.com/api/translate/web/ecommerce";
        String accessKeyId = "LTAI5t6jcnFkzK3eGbibPPj3";// 使用您的阿里云访问密钥 AccessKeyId
        String accessKeySecret = "89m7VwapNZT0DFEg5atJD0LfEv9iao"; // 使用您的阿里云访问密钥
        String postBody = "{\n" +
                " \"FormatType\": \"text\",\n" +
                " \"SourceLanguage\": \"zh\",\n" +
                " \"TargetLanguage\": \"ja\",\n" +
                " \"SourceText\": \"大疆无人机\",\n" +
                " \"Scene\": \"title\"\n" +
                "}";
        // Sender代码请参考帮助文档“签名方法”
        String result = Sender.sendPost(serviceURL, postBody, accessKeyId, accessKeySecret);
        System.out.println(result);
    }
}
