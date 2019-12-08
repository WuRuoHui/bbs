/**
 * @program: bbs
 * @description:
 * @author: Wu
 * @create: 2019-12-07 10:50
 **/
package com.wu.bbs.mapper;

import com.wu.bbs.entity.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    @Insert("insert into QUESTION(title,description,gmt_create,gmt_modified,creator) values (#{title},#{description},#{gmt_create},#{gmt_modified},#{creator})")
    void create(Question question);
}
