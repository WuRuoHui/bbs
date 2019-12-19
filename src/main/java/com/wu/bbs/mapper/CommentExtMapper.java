package com.wu.bbs.mapper;

import com.wu.bbs.entity.Comment;
import org.apache.ibatis.annotations.Param;

public interface CommentExtMapper {

    int incCommentCount(@Param("comment") Comment comment);

}