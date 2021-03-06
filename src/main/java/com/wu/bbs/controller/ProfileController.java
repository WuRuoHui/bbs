/**
 * @program: bbs
 * @description:
 * @author: Wu
 * @create: 2019-12-10 16:39
 **/
package com.wu.bbs.controller;

import com.github.pagehelper.PageInfo;
import com.wu.bbs.dto.CommentDTO;
import com.wu.bbs.dto.NotificationDTO;
import com.wu.bbs.enums.CommentTypeEnum;
import com.wu.bbs.dto.QuestionDTO;
import com.wu.bbs.entity.User;
import com.wu.bbs.service.CommentService;
import com.wu.bbs.service.NotificationService;
import com.wu.bbs.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private PublishService publishService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private NotificationService notificationService;

    @RequestMapping("/profile/{action}")
    public String showProfile(@PathVariable(name = "action") String action, Model model, HttpServletRequest request, @RequestParam(defaultValue = "1") Integer currentPage,
                              @RequestParam(defaultValue = "5") Integer size) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) return "redirect:/";
        if (action.equals("questions")) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
            PageInfo<QuestionDTO> paginationDTO = publishService.getAllQuestion(currentPage, size);
//            System.out.println(paginationDTO.toString());
            model.addAttribute("paginationDTO", paginationDTO);
        } else if (action.equals("replies")) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
            PageInfo<NotificationDTO> notificationDTOPageInfo = notificationService.list(user.getId(),currentPage,size);
            model.addAttribute("paginationDTO", notificationDTOPageInfo);
        }
        return "profile";
    }

    @RequestMapping("/profile/questions/{id}")
    public String findProfileById(@PathVariable(name = "id") Integer id, Model model) {
        QuestionDTO questionDTO = publishService.findQuestionById(id);
        //增加阅读数
        publishService.incView(id);
        List<QuestionDTO> questionDTOList = publishService.selectRelated(questionDTO);
        System.out.println(questionDTO);
        List<CommentDTO> commentDTOList = commentService.listByQuestionId(Long.valueOf(id), CommentTypeEnum.QUESTION.getType());
        model.addAttribute("questionDTO", questionDTO);
        model.addAttribute("commentDTOList",commentDTOList);
        model.addAttribute("questionDTOList",questionDTOList);
        return "question";
    }

}
