/**
 * @program: bbs
 * @description: GitHub第三方登录认证controller
 * @author: Wu
 * @create: 2019-11-24 22:39
 **/
package com.wu.bbs.controller;

import com.wu.bbs.dto.GithubUser;
import com.wu.bbs.service.AuthorizeService;
import com.wu.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthorizeService authorizeService;

    @GetMapping("/callback")
    public String callback(String code, String state, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(code + ":" + state);
        String accessToken = authorizeService.getAccessToken(code, state);
        GithubUser githubUser = authorizeService.getUser(accessToken);
        System.out.println(accessToken);
        System.out.println(githubUser.getId() + ":" + githubUser.getName());
        if (githubUser != null && githubUser.getId() != null) {
            String token = UUID.randomUUID().toString();
            userService.insert(githubUser, token);
            //登录成功，写cookie和session
//            request.getSession().setAttribute("user", githubUser);
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
            return "redirect:/";
        } else {
            return "redirect:index";
        }
    }
}
