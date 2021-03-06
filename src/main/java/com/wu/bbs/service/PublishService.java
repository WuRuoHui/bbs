/**
 * @program: bbs
 * @description: 发布接口
 * @author: Wu
 * @create: 2019-12-08 09:02
 **/
package com.wu.bbs.service;

import com.github.pagehelper.PageInfo;
import com.wu.bbs.dto.QuestionDTO;
import com.wu.bbs.entity.Question;
import com.wu.bbs.entity.User;

import java.util.List;

public interface PublishService {
    String doPublish(Question question, User user);
    PageInfo<QuestionDTO> getAllQuestion(Integer currentPage, Integer size);
    QuestionDTO findQuestionById(Integer id);

    void createOrUpdate(Question question);

    void incView(Integer id);

    List<QuestionDTO> selectRelated(QuestionDTO questionDTO);
}