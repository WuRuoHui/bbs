/**
 * @program: bbs
 * @description: 第一次使用springboot
 * @author: Wu
 * @create: 2019-11-23 08:28
 **/
package com.wu.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello") //RequestMapping的缩写方式，表示method是GET方式
    public String hello() {
        return "index";
    }
}