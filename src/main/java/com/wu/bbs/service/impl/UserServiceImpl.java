/**
 * @program: bbs
 * @description: 用户接口实现类
 * @author: Wu
 * @create: 2019-12-08 10:48
 **/
package com.wu.bbs.service.impl;

import com.wu.bbs.dto.GithubUser;
import com.wu.bbs.entity.User;
import com.wu.bbs.mapper.UserMapper;
import com.wu.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insert(GithubUser githubUser,String token) {
        User user = new User();
        user.setToken(token);
        user.setName(githubUser.getName());
        user.setAccount_id(String.valueOf(githubUser.getId()));
        user.setAvatarUrl(githubUser.getAvatar_url());
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(System.currentTimeMillis());
        userMapper.insert(user);
    }

    @Override
    public User getUserByToken(String token) {
        User user = userMapper.getUserByToken(token);
        return user;
    }

    @Override
    public void updateUserTokenById(Integer id ,String token) {
        userMapper.updateUserTokenById(id,token);
    }
}