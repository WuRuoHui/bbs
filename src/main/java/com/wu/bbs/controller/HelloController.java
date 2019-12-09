/**
 * @program: bbs
 * @description: 第一次使用springboot
 * @author: Wu
 * @create: 2019-11-23 08:28
 **/
package com.wu.bbs.controller;

import com.wu.bbs.dto.QuestionDTO;
import com.wu.bbs.entity.User;
import com.wu.bbs.service.PublishService;
import com.wu.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HelloController {

    @Autowired
    private UserService userService;
    @Autowired
    private PublishService publishService;

    @GetMapping("/") //RequestMapping的缩写方式，表示method是GET方式
    public String hello(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userService.getUserByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        List<QuestionDTO> questionDTOList = publishService.getAllQuestion();
        System.out.println(questionDTOList.toString());
        model.addAttribute("questionList",questionDTOList);
        return "index";
    }
}