/**
 * @program: bbs
 * @description: 认证service接口实现类
 * @author: Wu
 * @create: 2019-12-09 01:09
 **/
package com.wu.bbs.service.impl;

import com.wu.bbs.dto.AccessTokenDTO;
import com.wu.bbs.dto.GithubUser;
import com.wu.bbs.provider.GithubProvider;
import com.wu.bbs.service.AuthorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String GITHUB_CLIENT_ID;
    @Value("${github.client.secret}")
    private String GITHUB_CLIENT_SECRET;
    @Value("${github.redirect.uri}")
    private String GITHUB_REDIRECT_URI;

    @Override
    public String getAccessToken(String code, String state) {

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(GITHUB_REDIRECT_URI);
        accessTokenDTO.setClient_secret(GITHUB_CLIENT_SECRET);
        accessTokenDTO.setClient_id(GITHUB_CLIENT_ID);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        return accessToken;

    }

    @Override
    public GithubUser getUser(String accessToken) {
        GithubUser githubUser = githubProvider.getUser(accessToken);
        return githubUser;
    }
}
