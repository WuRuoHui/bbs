/**
 * @program: bbs
 * @description: 评论controller
 * @author: Wu
 * @create: 2019-12-15 09:35
 **/
package com.wu.bbs.controller;

import com.wu.bbs.dto.CommentCreateDTO;
import com.wu.bbs.dto.ResultDTO;
import com.wu.bbs.entity.User;
import com.wu.bbs.exception.CustomizeErrorCode;
import com.wu.bbs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @PostMapping("/comment")
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO, HttpServletRequest request) {
        System.out.println(commentCreateDTO);
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.USET_NOT_FOUND);
        }
        commentService.insert(commentCreateDTO, user);
        return ResultDTO.okOf();
    }
}