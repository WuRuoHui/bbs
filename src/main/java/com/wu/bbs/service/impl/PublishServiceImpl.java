/**
 * @program: bbs
 * @description: 发布接口实现类
 * @author: Wu
 * @create: 2019-12-08 09:04
 **/
package com.wu.bbs.service.impl;

import com.wu.bbs.dto.GithubUser;
import com.wu.bbs.dto.QuestionDTO;
import com.wu.bbs.entity.Question;
import com.wu.bbs.entity.User;
import com.wu.bbs.mapper.QuestionMapper;
import com.wu.bbs.mapper.UserMapper;
import com.wu.bbs.service.PublishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublishServiceImpl implements PublishService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public String doPublish(Question question, GithubUser user) {
        try {
            questionMapper.create(question);
        }catch (Exception e) {
            return "fail";
        }
        return "success";
    }

    @Override
    public List<QuestionDTO> getAllQuestion() {
        List<Question> questionList = questionMapper.getAllQuestion();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.findUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
