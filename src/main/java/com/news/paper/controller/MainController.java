package com.news.paper.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.news.paper.DB.Message;
import com.news.paper.DB.User;
import com.news.paper.DB.Views;
import com.news.paper.repo.MessageRepo;
import com.news.paper.repo.UserRepo;
import com.news.paper.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.sql.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Controller
public class MainController {
    private final MessageRepo messageRepo;
    private final UserRepo userRepo;

    private final ObjectWriter messageWriter;
    private final ObjectWriter profileWritter;

    @Autowired
    private MessageService messageService;

    @Autowired
    public MainController(MessageRepo messageRepo, UserRepo userRepo, ObjectMapper mapper) {
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;


        ObjectMapper objectMapper = mapper.setConfig(mapper.getSerializationConfig());

        this.messageWriter =  mapper
                .setConfig(mapper.getSerializationConfig())
                .writerWithView(Views.FullMessage.class);

        this.profileWritter =  mapper
                .setConfig(mapper.getSerializationConfig())
                .writerWithView(Views.FullProfile.class);
//
    }

    @GetMapping("/")
    public String main(Model model, @AuthenticationPrincipal User user) throws JsonProcessingException {

        HashMap<Object, Object> data = new HashMap<>();



//        String subscriptionsMessage = "[]";

        String messages = messageWriter.writeValueAsString(messageService.cutMessages(messageRepo.findAll()));

        model.addAttribute("messages", messages);

        if( user != null)
        {
            data.put("usrID", user.getId());

            User userFromDb = userRepo.findById(user.getId()).get();
            String serializedProfile = profileWritter.writeValueAsString(userFromDb);

//            String subscriptionsMessage = messageWriter.writeValueAsString(messageService.loadMessageBySubscriber(user));
//            model.addAttribute("subscriptionsMessage", subscriptionsMessage);

            model.addAttribute("profile", serializedProfile);
            model.addAttribute("userName", user.getUsername());

            model.addAttribute("frontendData", data);



        }
        else {


//            data.put("usrID", 30);
            model.addAttribute("frontendData", data);
//
//        model.addAttribute("profile", null);
//
            model.addAttribute("userName", "Guest");


        }

//        model.addAttribute("subscriptionsMessage", messages);




//        model.addAttribute("subscriptionsMessage", subscriptionsMessage);


        return "index";
    }

}
