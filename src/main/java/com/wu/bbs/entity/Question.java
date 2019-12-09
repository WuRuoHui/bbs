/**
 * @program: bbs
 * @description: 问题
 * @author: Wu
 * @create: 2019-12-07 11:02
 **/
package com.wu.bbs.entity;

import lombok.Data;

@Data
public class Question {

    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private String tag;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;

}
