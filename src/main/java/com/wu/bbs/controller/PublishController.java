/**
 * @program: bbs
 * @description: 发布controller
 * @author: Wu
 * @create: 2019-12-07 08:37
 **/
package com.wu.bbs.controller;

import com.wu.bbs.dto.GithubUser;
import com.wu.bbs.entity.Question;
import com.wu.bbs.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(String title, String description, String tag, HttpServletRequest request, Model model) {
        GithubUser user = (GithubUser) request.getSession().getAttribute("user");
        if (user==null) {
            model.addAttribute("error","请登录");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(Integer.valueOf( user.getId()+""));
        question.setGmt_create(System.currentTimeMillis());
        question.setGmt_modified(System.currentTimeMillis());
        questionMapper.create(question);
        return "redirect:/";
    }
}