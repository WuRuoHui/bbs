package com.wu.bbs.service;

import com.wu.bbs.dto.CommentCreateDTO;
import com.wu.bbs.dto.CommentDTO;
import com.wu.bbs.entity.User;

import java.util.List;

public interface CommentService {
    void insert(CommentCreateDTO commentCreateDTO, User user);

    List<CommentDTO> listByQuestionId(Long id, Integer type);
}