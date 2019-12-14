/**
 * @program: bbs
 * @description: 自定义异常
 * @author: Wu
 * @create: 2019-12-14 00:00
 **/
package com.wu.bbs.exception;

public class CustomizeException extends RuntimeException {

    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    public CustomizeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
