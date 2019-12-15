/**
 * @program: bbs
 * @description:
 * @author: Wu
 * @create: 2019-12-10 16:39
 **/
package com.wu.bbs.controller;

import com.github.pagehelper.PageInfo;
import com.wu.bbs.dto.QuestionDTO;
import com.wu.bbs.entity.User;
import com.wu.bbs.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private PublishService publishService;

    @RequestMapping("/profile/{action}")
    public String showProfile(@PathVariable(name = "action") String action, Model model, HttpServletRequest request, @RequestParam(defaultValue = "1") Integer currentPage,
                              @RequestParam(defaultValue = "5") Integer size) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) return "redirect:/";
        if (action.equals("questions")) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
        } else if (action.equals("replies")) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }
        PageInfo<QuestionDTO> paginationDTO = publishService.getAllQuestion(currentPage, size);
        System.out.println(paginationDTO.toString());
        model.addAttribute("paginationDTO", paginationDTO);
        return "profile";
    }

    @RequestMapping("/profile/questions/{id}")
    public String findProfileById(@PathVariable(name = "id") Integer id, Model model) {
        QuestionDTO questionDTO = publishService.findQuestionById(id);
        //增加阅读数
        publishService.incView(id);
        System.out.println(questionDTO);
        model.addAttribute("questionDTO", questionDTO);
        return "question";
    }

}
