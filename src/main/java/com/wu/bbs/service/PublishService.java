/**
 * @program: bbs
 * @description: 发布接口
 * @author: Wu
 * @create: 2019-12-08 09:02
 **/
package com.wu.bbs.service;

import com.wu.bbs.dto.GithubUser;
import com.wu.bbs.dto.QuestionDTO;
import com.wu.bbs.entity.Question;

import java.util.List;

public interface PublishService {
    String doPublish(Question question, GithubUser user);
    List<QuestionDTO> getAllQuestion();
}