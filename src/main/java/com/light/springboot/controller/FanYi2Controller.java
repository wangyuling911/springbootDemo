package com.light.springboot.controller;

import com.aliyun.tea.*;
import com.aliyun.alimt20181012.*;
import com.aliyun.alimt20181012.models.*;
import com.aliyun.teaopenapi.*;
import com.aliyun.teaopenapi.models.*;
import org.apache.http.protocol.HTTP;

public class FanYi2Controller {

    /**
     * 使用AK&SK初始化账号Client
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.alimt20181012.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "mt.aliyuncs.com";
        return new com.aliyun.alimt20181012.Client(config);
    }

    public static void mainsss(String[] args_) throws Exception {
        java.util.List<String> args = java.util.Arrays.asList(args_);
        com.aliyun.alimt20181012.Client client = FanYi2Controller.createClient("LTAI5t6jcnFkzK3eGbibPPj3", "89m7VwapNZT0DFEg5atJD0LfEv9iao");
        TranslateGeneralRequest translateGeneralRequest = new TranslateGeneralRequest()
                .setFormatType("text")
                .setSourceLanguage("zh")
                .setTargetLanguage("ja")
                .setSourceText("你好");
        // 复制代码运行请自行打印 API 的返回值
        TranslateGeneralResponse translateGeneralResponse = client.translateGeneral(translateGeneralRequest);
        if (translateGeneralResponse!=null&& translateGeneralResponse.getBody().getCode()==200) {

            TranslateGeneralResponseBody.TranslateGeneralResponseBodyData data = translateGeneralResponse.getBody().getData();
        }
        System.out.println(translateGeneralResponse.toString());
    }

    public static void main(String[] args_) throws Exception {
        java.util.List<String> args = java.util.Arrays.asList(args_);
        com.aliyun.alimt20181012.Client client = FanYi2Controller.createClient("LTAI5t6jcnFkzK3eGbibPPj3", "89m7VwapNZT0DFEg5atJD0LfEv9iao");
        GetDetectLanguageRequest getDetectLanguageRequest = new GetDetectLanguageRequest()
                .setSourceText("");
        // 复制代码运行请自行打印 API 的返回值
        GetDetectLanguageResponse detectLanguage = client.getDetectLanguage(getDetectLanguageRequest);
        System.out.println(detectLanguage);
    }
}
