/**
 * @program: bbs
 * @description: GitHub第三方登录认证controller
 * @author: Wu
 * @create: 2019-11-24 22:39
 **/
package com.wu.bbs.controller;

import com.wu.bbs.dto.AccessTokenDTO;
import com.wu.bbs.dto.GithubUser;
import com.wu.bbs.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String GITHUB_CLIENT_ID;
    @Value("${github.client.secret}")
    private String GITHUB_CLIENT_SECRET;
    @Value("${github.redirect.uri}")
    private String GITHUB_REDIRECT_URI;

    @GetMapping("/callback")
    public String callback(String code, String state, HttpServletRequest request) {
        System.out.println(code + ":" + state);
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(GITHUB_REDIRECT_URI);
        accessTokenDTO.setClient_secret(GITHUB_CLIENT_SECRET);
        accessTokenDTO.setClient_id(GITHUB_CLIENT_ID);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(accessToken);
        System.out.println(user.getId() + ":" + user.getName());
        if (user != null) {
            //登录成功，写cookie和session
            request.getSession().setAttribute("user", user);
            return "redirect:/";
        } else {
            return "redirect:index";
        }
    }
}
