/**
 * @program: bbs
 * @description: 用户类
 * @author: Wu
 * @create: 2019-12-08 09:39
 **/
package com.wu.bbs.entity;

import lombok.Data;

@Data
public class User {

    private Integer id;
    private String account_id;
    private String name;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String bio;
    private String avatarUrl;

}
