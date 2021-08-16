package com.light.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    /**
     * 处理 Exception 类型的异常
     * @param e
     * @return
     */
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
    public Map<String,Object> defaultExceptionHandler(Exception e) {
        System.out.println("北京");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code", 500);
        map.put("msg", e.getMessage());
        return map;
    }

    /**
     * 处理 Exception 类型的异常
     * @param e
     * @return
     */
    @ExceptionHandler(RobotCooperationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Map<String,Object> defaultExceptionHandler(RobotCooperationException e) {
        List<String> objects = new ArrayList<>();
        for (String o:objects) {
            System.out.println(o);
        }

        return null;
    }
}