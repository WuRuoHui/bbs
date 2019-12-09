/**
 * @program: bbs
 * @description: GitHub用户
 * @author: Wu
 * @create: 2019-11-25 00:20
 **/
package com.wu.bbs.dto;

import lombok.Data;

@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;
}
