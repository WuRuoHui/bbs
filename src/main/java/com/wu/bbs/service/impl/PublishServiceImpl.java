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
import com.wu.bbs.entity.QuestionExample;
import com.wu.bbs.entity.User;
import com.wu.bbs.exception.CustomizeErrorCode;
import com.wu.bbs.exception.CustomizeException;
import com.wu.bbs.mapper.QuestionExtMapper;
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
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public String doPublish(Question question, User user) {
        try {
            questionMapper.insert(question);
        } catch (Exception e) {
            return "fail";
        }
        return "success";
    }

    @Override
    public PageInfo<QuestionDTO> getAllQuestion(Integer currentPage, Integer size) {
        Integer totalCount = Math.toIntExact(questionMapper.countByExample(new QuestionExample()));    //获得总记录数
        int pages = (totalCount + size - 1) / size;                   //获得总页数
//        int offset = (currentPage - 1) * size;
        if (currentPage < 1) {
            currentPage = 1;
        }
        if (currentPage > pages) {
            currentPage = pages;
        }
        PageHelper.startPage(currentPage, size);
        List<Question> questionList = questionMapper.selectByExample(new QuestionExample());
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
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

    @Override
    public QuestionDTO findQuestionById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    @Override
    public void createOrUpdate(Question question) {
        System.out.println("question：" + question.getId());
        question.setGmtModified(System.currentTimeMillis());
        if (question.getId() == null) {
            question.setGmtCreate(System.currentTimeMillis());
            questionMapper.insertSelective(question);
        } else {
            System.out.println(question.getId());
            System.out.println(question.toString());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            int rows = questionMapper.updateByExampleSelective(question, example);
            if (rows != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    @Override
    public void incView(Integer id) {
        Question question = new Question();
        question.setViewCount(1);
        question.setId(id);
        questionExtMapper.incView(question);
        System.out.println(question);
    }
}
