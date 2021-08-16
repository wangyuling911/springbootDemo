package com.light.springboot.exception;

/**
 * 自定义异常
 */
public class RobotCooperationException extends RuntimeException {
    private static final long serialVersionUID = -5091178823314871568L;

    public RobotCooperationException(String string) {
        super(string);
    }

}
