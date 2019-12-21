/**
 * @program: bbs
 * @description:
 * @author: Wu
 * @create: 2019-12-20 22:07
 **/
package com.wu.bbs.dto;

import lombok.Data;

import java.util.List;

@Data
public class TagCacheDTO {
    private String categoryName;
    private List<String> tags;
}
