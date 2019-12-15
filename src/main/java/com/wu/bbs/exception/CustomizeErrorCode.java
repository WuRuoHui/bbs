package com.wu.bbs.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{

    QUESTION_NOT_FOUND(2001,"你找的问题不存在了，要不换个试试"),
    TARGET_PARAM_NOT_FOUND(2002,"未选择任何问题或者评论"),
    USET_NOT_FOUND(2003,"未登录")
    ;

    private Integer code;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
