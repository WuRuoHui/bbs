/**
 * @program: bbs
 * @description: 传输层question
 * @author: Wu
 * @create: 2019-12-09 15:28
 **/
package com.wu.bbs.dto;

import com.wu.bbs.entity.User;
import lombok.Data;

@Data
public class QuestionDTO {
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
    private User user;
}
