package com.wu.bbs.service;

import com.wu.bbs.dto.GithubUser;

public interface AuthorizeService {

    String getAccessToken(String code, String state);
    GithubUser getUser(String accessToken);
}
