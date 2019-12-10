/**
 * @program: bbs
 * @description:
 * @author: Wu
 * @create: 2019-12-07 10:50
 **/
package com.wu.bbs.mapper;

import com.wu.bbs.entity.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Mapper
public interface QuestionMapper {
    @Insert("insert into QUESTION(title,description,gmt_create,gmt_modified,creator) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator})")
    void create(Question question);

    @Select("select * from question")
    List<Question> getAllQuestion();

    @Select("select count(1) from question")
    Integer getAllQuestionCount();
}