/**
 * @program: bbs
 * @description: 回复服务实现类
 * @author: Wu
 * @create: 2019-12-15 09:51
 **/
package com.wu.bbs.service.impl;

import com.wu.bbs.dto.CommentDTO;
import com.wu.bbs.entity.Comment;
import com.wu.bbs.entity.User;
import com.wu.bbs.exception.CustomizeErrorCode;
import com.wu.bbs.exception.CustomizeException;
import com.wu.bbs.mapper.CommentMapper;
import com.wu.bbs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void insert(CommentDTO commentDTO, User user) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        if (commentDTO.getParentId() == null) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(1);
        commentMapper.insertSelective(comment);
    }
}
