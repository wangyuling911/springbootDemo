package com.light.springboot.controller;

import com.google.common.collect.Lists;
import com.light.springboot.SocketMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@Slf4j
public class TestController {


    @GetMapping("/helloworld")
    public String helloworld(int s) {
        return "beijign";
    }

    @GetMapping("/stop")
    public String stop() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            System.out.println(i+"babbabge");
            Thread.sleep(1000);
        }
        return "beijign";
    }

    public static void main21(String[] args) {

        List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
        garbageCollectorMXBeans.forEach(a -> {
            System.out.println(a.getName());
        });
    }

    public void sdfsa(){
        Method[] methods = getClass().getMethods();
        System.out.println(methods.length);
    }

    public static <T> T convert(List<?> fromValue, Class<T> toValueType) {
        //System.out.println(toValueType.getComponentType().isAssignableFrom(fromValue.get(0).getClass()));
        System.out.println(toValueType.isAssignableFrom(fromValue.get(0).getClass()));
        T t = (T) fromValue.get(0);

        return null;
    }


	public static void main(String[] args) {
		try {
			String secret = "my-secret-key";
			String message = "POST\n" +
					"/app-hmac/user/get/encrypt/secret\n" +
					"\n" +
					"user-key\n" +
					"Wed, 22 Mar 2023 07:51:58 GMT\n" +
					"User-Agent:PostmanRuntime/7.31.3\n";

			String message2 = "{\"email\": \"1134344850@qq.com\"}";


			Mac hasher = Mac.getInstance("HmacSHA256");
			hasher.init(new SecretKeySpec(secret.getBytes(), "HmacSHA256"));

			byte[] hash = hasher.doFinal(message2.getBytes());

			// to lowercase hexits
			String s = DatatypeConverter.printHexBinary(hash);
			System.out.println(s);

			// to base64
			String s1 = DatatypeConverter.printBase64Binary(hash);
			System.out.println(s1);
		} catch (NoSuchAlgorithmException e) {
		} catch (InvalidKeyException e) {
		}
	}


}