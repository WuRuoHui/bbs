/**
 * @program: bbs
 * @description: 请求github登录获取token的参数数据传输类
 * @author: Wu
 * @create: 2019-11-24 23:01
 **/
package com.wu.bbs.dto;

import lombok.Data;

@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}