package com.wu.bbs.mapper;

import com.wu.bbs.entity.Question;
import org.apache.ibatis.annotations.Param;

public interface QuestionExtMapper {

    int incView(@Param("question") Question question);

}