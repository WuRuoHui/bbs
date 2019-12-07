/**
 * @program: bbs
 * @description: 发布controller
 * @author: Wu
 * @create: 2019-12-07 08:37
 **/
package com.wu.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublishController {

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

}
