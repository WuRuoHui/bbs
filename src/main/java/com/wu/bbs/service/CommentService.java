package com.wu.bbs.service;

import com.wu.bbs.dto.CommentDTO;
import com.wu.bbs.entity.User;

public interface CommentService {
    void insert(CommentDTO commentDTO, User user);
}