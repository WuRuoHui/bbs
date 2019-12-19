/**
 * @program: bbs
 * @description: 评论数据传输DTO
 * @author: Wu
 * @create: 2019-12-16 20:46
 **/
package com.wu.bbs.dto;

import com.wu.bbs.entity.User;
import lombok.Data;

@Data
public class CommentDTO {
    private Long id;

    private Long parentId;

    private Integer type;

    private Integer commentator;

    private Long gmtCreate;

    private Long gmtModified;

    private Long likeCount;

    private String content;

    private User user;

    private Integer commentCount;
}