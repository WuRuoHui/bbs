/**
 * @program: bbs
 * @description: 用户接口实现类
 * @author: Wu
 * @create: 2019-12-08 10:48
 **/
package com.wu.bbs.service.impl;

import com.wu.bbs.dto.GithubUser;
import com.wu.bbs.entity.User;
import com.wu.bbs.entity.UserExample;
import com.wu.bbs.mapper.UserMapper;
import com.wu.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insert(GithubUser githubUser, String token) {
        User user = new User();
        user.setToken(token);
        user.setName(githubUser.getName());
        user.setAccountId(String.valueOf(githubUser.getId()));
        user.setAvatarUrl(githubUser.getAvatar_url());
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(System.currentTimeMillis());
        userMapper.insert(user);
    }

    @Override
    public User getUserByToken(String token) {
        UserExample example = new UserExample();
        example.createCriteria().andTokenEqualTo(token);
        List<User> userList = userMapper.selectByExample(example);
        return userList.get(0);
    }

    @Override
    public void updateUserTokenById(Integer id, String token) {
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(id);
        User user = userMapper.selectByPrimaryKey(id);
        user.setToken(token);
        userMapper.updateByExample(user, example);
    }

    @Override
    public void createOrUpdate(GithubUser githubUser, String token) {
        UserExample example = new UserExample();
        example.createCriteria().andAccountIdEqualTo(githubUser.getId().toString());
        List<User> userList = userMapper.selectByExample(example);
        if (userList != null && userList.size() != 0) {
         /*   User updateUser = new User();
            updateUser.setToken(token);
            updateUser.setGmtModified(System.currentTimeMillis());*/
            userList.get(0).setToken(token);
            userList.get(0).setGmtModified(System.currentTimeMillis());
            userMapper.updateByExample(userList.get(0),example);
/*            UserExample example1 = new UserExample();
            userMapper.updateByExampleSelective(updateUser, example1);*/
        } else {
            insert(githubUser, token);
        }
    }
}