/**
 * @program: bbs
 * @description: 列表DTO
 * @author: Wu
 * @create: 2019-12-09 23:53
 **/
package com.wu.bbs.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaginationDTO<T> {
    private List<T> list;
    private Integer currentPage;     //当前页
    private Integer totalPage;   //总页数
    private Integer totalCount;  //总记录数
    private Integer size;    //每页显示记录数
}