package com.news.paper.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.news.paper.DB.User;
import com.news.paper.DB.Views;
import com.news.paper.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.news.paper.repo.MessageRepo;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ProfileController {
    private final MessageRepo messageRepo;
    private final UserRepo userRepo;

    private final ObjectWriter messageWriter;
    private final ObjectWriter profileWritter;

    @Autowired
    public ProfileController(MessageRepo messageRepo, UserRepo userRepo, ObjectMapper mapper) {

        this.messageRepo = messageRepo;
        this.userRepo = userRepo;


        ObjectMapper objectMapper = mapper.setConfig(mapper.getSerializationConfig());

        this.messageWriter =  mapper
                .setConfig(mapper.getSerializationConfig())
                .writerWithView(Views.FullMessage.class);

        this.profileWritter =  mapper
                .setConfig(mapper.getSerializationConfig())
                .writerWithView(Views.FullProfile.class);

    }

    @GetMapping("/prututofile")
    public String Myprofile(Model model, @AuthenticationPrincipal User user) throws JsonProcessingException {
//
        String messages = messageWriter.writeValueAsString(messageRepo.findByAuthor(user));
//
        return "profile";
    }

//    @GetMapping("/profile/{id}")
//    public String profile(Model model, @AuthenticationPrincipal User user) throws JsonProcessingException {
//
//        String messages = messageWriter.writeValueAsString(messageRepo.findByAuthor(user));
//
//        return "profile";
//    }


}
