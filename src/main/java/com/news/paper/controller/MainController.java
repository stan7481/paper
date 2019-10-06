package com.news.paper.controller;

import com.news.paper.DB.User;
import com.news.paper.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {
    private final MessageRepo messageRepo;

    @Autowired
    public MainController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping("/")
    public String main(Model model) {
      //  UsernamePasswordAuthenticationToken authToken
//        Object data1 = authToken.getPrincipal();
//        data1.getUsername();
//        @RequestParam(required=false) User user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        HashMap<Object, Object> data = new HashMap<>();

//        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal;
//        name = user.getUsername()

        data.put("messages", messageRepo.findAll());

        model.addAttribute("frontendData", data);
        model.addAttribute("userName", currentPrincipalName);

          return "index";
    }

//    @GetMapping("/")
//    public String main(User user, Model model) {
//
//        HashMap<Object, Object> data = new HashMap<>();
//
//        data.put("messages", messageRepo.findAll());
//
//        model.addAttribute("frontendData", data);
//
//        return "index";
//    }

}
