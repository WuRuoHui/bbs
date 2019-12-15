/**
 * @program: bbs
 * @description:
 * @author: Wu
 * @create: 2019-12-15 09:39
 **/
package com.wu.bbs.dto;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
