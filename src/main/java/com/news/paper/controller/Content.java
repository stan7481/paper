package com.news.paper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Content {

    @GetMapping("content1")
    public String test(){

        return "content1";
    }
    
    @GetMapping("content2")
    public String test2(){

        return "content2";
    }

}
