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

public interface PublishService {
    String doPublish(Question question, User user);
    PageInfo<QuestionDTO> getAllQuestion(Integer currentPage, Integer size);
}