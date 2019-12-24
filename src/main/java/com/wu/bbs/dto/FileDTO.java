/**
 * @program: bbs
 * @description: 文件上传返回值DTO
 * @author: Wu
 * @create: 2019-12-24 08:50
 **/
package com.wu.bbs.dto;

import lombok.Data;

@Data
public class FileDTO {
    private int success;
    private String message;
    private String url;
}
