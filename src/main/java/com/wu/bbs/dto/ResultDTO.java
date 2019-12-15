/**
 * @program: bbs
 * @description: json数据类型返回DTO
 * @author: Wu
 * @create: 2019-12-15 10:34
 **/
package com.wu.bbs.dto;

import com.wu.bbs.exception.CustomizeErrorCode;
import lombok.Data;

@Data
public class ResultDTO {
    private Integer code;
    private String message;

    public ResultDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResultDTO okOf() {
        return new ResultDTO(200,"成功");
    }

    public static ResultDTO errorOf(Integer code, String message) {
        return new ResultDTO(code,message);
    }


    public static ResultDTO errorOf(CustomizeErrorCode errorCode) {
        return new ResultDTO(errorCode.getCode(),errorCode.getMessage());
    }
}