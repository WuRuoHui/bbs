/**
 * @program: bbs
 * @description: 回复服务实现类
 * @author: Wu
 * @create: 2019-12-15 09:51
 **/
package com.wu.bbs.service.impl;

import com.wu.bbs.dto.CommentCreateDTO;
import com.wu.bbs.dto.CommentDTO;
import com.wu.bbs.dto.CommentTypeEnum;
import com.wu.bbs.entity.*;
import com.wu.bbs.exception.CustomizeErrorCode;
import com.wu.bbs.exception.CustomizeException;
import com.wu.bbs.mapper.CommentMapper;
import com.wu.bbs.mapper.QuestionExtMapper;
import com.wu.bbs.mapper.QuestionMapper;
import com.wu.bbs.mapper.UserMapper;
import com.wu.bbs.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Override
    @Transactional
    public void insert(CommentCreateDTO commentCreateDTO, User user) {
        Comment comment = new Comment();
        comment.setContent(commentCreateDTO.getContent());
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        if (commentCreateDTO.getParentId() == null) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (commentCreateDTO.getType() == null || !CommentTypeEnum.isExit(commentCreateDTO.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (commentCreateDTO.getType() == CommentTypeEnum.COMMENT.getType()) {
            //回复评论
            Comment selectByPrimaryKey = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (selectByPrimaryKey == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insertSelective(comment);
        } else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId().intValue());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insertSelective(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
        }
    }

    @Override
    public List<CommentDTO> listByQuestionId(Long id, Integer type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type);
        List<Comment> commentList = commentMapper.selectByExample(commentExample);
        if (commentList == null || commentList.size()==0) {
            return new ArrayList<>();
        }
        //获取去重的评论人
        Set<Integer> commentatorSet = commentList.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Integer> userIds = new ArrayList<>();
        userIds.addAll(commentatorSet);
        //获取评论人并转化为map
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);
        List<User> userList = userMapper.selectByExample(userExample);
        System.out.println(userList);
        Map<Integer, User> userMap = userList.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        System.out.println(userMap);
        //转换comment为commentDTO
        List<CommentDTO> commentDTOList = commentList.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOList;
    }
}