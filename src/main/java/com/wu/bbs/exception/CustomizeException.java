/**
 * @program: bbs
 * @description: 自定义异常
 * @author: Wu
 * @create: 2019-12-14 00:00
 **/
package com.wu.bbs.exception;

public class CustomizeException extends RuntimeException {

    private Integer code;
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
