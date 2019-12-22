/**
 * @program: bbs
 * @description: 发布controller
 * @author: Wu
 * @create: 2019-12-07 08:37
 **/
package com.wu.bbs.controller;

import com.wu.bbs.cache.TagCache;
import com.wu.bbs.dto.QuestionDTO;
import com.wu.bbs.entity.Question;
import com.wu.bbs.entity.User;
import com.wu.bbs.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private PublishService publishService;

    @GetMapping("/profile/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id, Model model) {
        QuestionDTO questionDTO = publishService.findQuestionById(id);
        model.addAttribute("title", questionDTO.getTitle());
        model.addAttribute("tag", questionDTO.getTag());
        model.addAttribute("description", questionDTO.getDescription());
        model.addAttribute("id", questionDTO.getId());
        TagCache tagCache = new TagCache();
        model.addAttribute("cacheTags", tagCache.get());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(Model model) {
        TagCache tagCache = new TagCache();
        model.addAttribute("cacheTags", tagCache.get());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(String title, String description, String tag, Integer id, HttpServletRequest request, Model model) {
        model.addAttribute("title", title);
        model.addAttribute("tag", tag);
        model.addAttribute("description", description);
        if (StringUtils.isEmpty(title)) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (StringUtils.isEmpty(description)) {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (StringUtils.isEmpty(tag)) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "请登录");
            return "publish";
        }
        Question question = new Question();
        question.setId(id);
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(Integer.valueOf(user.getId() + ""));
        publishService.createOrUpdate(question);
        return "redirect:/";
    }
}