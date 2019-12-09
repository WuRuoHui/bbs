package com.wu.bbs.service;

import com.wu.bbs.dto.GithubUser;
import com.wu.bbs.entity.User;

public interface UserService {

    void insert(GithubUser githubUser,String token);

    User getUserByToken(String token);
}