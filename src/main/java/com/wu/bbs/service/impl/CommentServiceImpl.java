/**
 * @program: bbs
 * @description: 回复服务实现类
 * @author: Wu
 * @create: 2019-12-15 09:51
 **/
package com.wu.bbs.service.impl;

import com.wu.bbs.dto.CommentDTO;
import com.wu.bbs.dto.CommentTypeEnum;
import com.wu.bbs.entity.Comment;
import com.wu.bbs.entity.Question;
import com.wu.bbs.entity.User;
import com.wu.bbs.exception.CustomizeErrorCode;
import com.wu.bbs.exception.CustomizeException;
import com.wu.bbs.mapper.CommentMapper;
import com.wu.bbs.mapper.QuestionExtMapper;
import com.wu.bbs.mapper.QuestionMapper;
import com.wu.bbs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Override
    @Transactional
    public void insert(CommentDTO commentDTO, User user) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(1);
        if (commentDTO.getParentId() == null) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (commentDTO.getType() == null || !CommentTypeEnum.isExit(commentDTO.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (commentDTO.getType() == CommentTypeEnum.COMMENT.getType()) {
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
}
