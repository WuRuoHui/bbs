/**
 * @program: bbs
 * @description: 发布接口实现类
 * @author: Wu
 * @create: 2019-12-08 09:04
 **/
package com.wu.bbs.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public String doPublish(Question question, User user) {
        try {
            questionMapper.create(question);
        } catch (Exception e) {
            return "fail";
        }
        return "success";
    }

    @Override
    public PageInfo<QuestionDTO> getAllQuestion(Integer currentPage, Integer size) {
        Integer totalCount = questionMapper.getAllQuestionCount();    //获得总记录数
        int pages = (totalCount + size - 1) / size;                   //获得总页数
//        int offset = (currentPage - 1) * size;
        if (currentPage < 1) {
            currentPage = 1;
        }
        if (currentPage > pages) {
            currentPage = pages;
        }
        PageHelper.startPage(currentPage, size);
        List<Question> questionList = questionMapper.getAllQuestion();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.findUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        PageInfo<QuestionDTO> questionDTOPage = new PageInfo<QuestionDTO>(questionDTOList);
        questionDTOPage.setPageSize(size);
        questionDTOPage.setPageNum(currentPage);
        questionDTOPage.setSize(totalCount);
        if (pages < 1) pages = 1;
        questionDTOPage.setPages(pages);
       /* PaginationDTO<QuestionDTO> paginationDTO = new PaginationDTO<>();
        paginationDTO.setCurrentPage(currentPage);     //设置分页bean的当前页
        paginationDTO.setSize(size);                   //设置每页显示条数
        paginationDTO.setList(questionDTOList);        //设置返回的QuestionDTO集合
        Integer totalCount = questionMapper.getAllQuestionCount();
        Integer totalPage = (totalCount + size - 1) / size;
        paginationDTO.setTotalPage(totalPage);*/
        return questionDTOPage;
    }
}
