/**
 * @program: bbs
 * @description: 评论controller
 * @author: Wu
 * @create: 2019-12-15 09:35
 **/
package com.wu.bbs.controller;

import com.wu.bbs.dto.CommentCreateDTO;
import com.wu.bbs.dto.CommentDTO;
import com.wu.bbs.dto.CommentTypeEnum;
import com.wu.bbs.dto.ResultDTO;
import com.wu.bbs.entity.User;
import com.wu.bbs.exception.CustomizeErrorCode;
import com.wu.bbs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO comments(@PathVariable(name = "id") Long id) {
        List<CommentDTO> commentDTOList = commentService.listByQuestionId(id, CommentTypeEnum.COMMENT.getType());
        return ResultDTO.okOf(commentDTOList);
    }
}