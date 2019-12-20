package com.wu.bbs.mapper;

import com.wu.bbs.entity.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionExtMapper {

    int incView(@Param("question") Question question);

    int incCommentCount(@Param("question") Question question);

    List<Question> selectRelated(Question question);

}