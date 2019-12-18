/**
 * @program: bbs
 * @description: json数据类型返回DTO
 * @author: Wu
 * @create: 2019-12-15 10:34
 **/
package com.wu.bbs.dto;

import com.wu.bbs.exception.CustomizeErrorCode;
import com.wu.bbs.exception.CustomizeException;
import lombok.Data;

@Data
public class ResultDTO<T> {
    private Integer code;
    private String message;
    private  T data;

    public ResultDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultDTO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResultDTO okOf() {
        return new ResultDTO(200,"成功");
    }

    public static <T> ResultDTO okOf(T t) {
        return new ResultDTO(200,"成功",t);
    }

    public static ResultDTO errorOf(Integer code, String message) {
        return new ResultDTO(code,message);
    }


    public static ResultDTO errorOf(CustomizeErrorCode errorCode) {
        return new ResultDTO(errorCode.getCode(),errorCode.getMessage());
    }

    public static ResultDTO errorOf(CustomizeException ex) {
        return new ResultDTO(ex.getCode(),ex.getMessage());
    }
}