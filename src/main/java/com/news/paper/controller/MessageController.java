package com.news.paper.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.function.BiConsumer;

import com.news.paper.DB.Message;

import com.news.paper.DB.User;
import com.news.paper.DB.Views;
import com.news.paper.dto.EventType;
import com.news.paper.dto.ObjectType;
import com.news.paper.repo.MessageRepo;
import com.news.paper.util.WsSender;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageRepo messageRepo;
    private final BiConsumer<EventType, Message> wsSender;

    @Autowired
    public MessageController(MessageRepo messageRepo, WsSender wsSender){
        this.messageRepo = messageRepo;

        this.wsSender = wsSender.getSender(ObjectType.MESSAGE, Views.IdName.class);
    }



//    public List<Message> list() {
    @GetMapping
    public String list(Model model) {
//        return messageRepo.findAll();
        model.addAttribute("frontendData", "text");
    return "index";
    }

    @GetMapping("{id}")
    public  Message getOne(@PathVariable("id") Message message){
        return message;
    }


    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        messageRepo.delete(message);
        wsSender.accept(EventType.REMOVE, message);
    }

    @PostMapping
    public Message create (@RequestBody Message message){
        message.setCreationDate(LocalDateTime.now());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User usr = (User)authentication.getPrincipal();
        Long userId = usr.getId();

        message.setAuthor(usr);

        //////////////
        Message updateMessage = messageRepo.save(message);
        wsSender.accept(EventType.CREATE, updateMessage);

         return updateMessage;
    }

    @PutMapping("{id}")
    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message)
    {
        BeanUtils.copyProperties(message, messageFromDb, "id", "author");

        Message updatedMessage = messageRepo.save(messageFromDb);
        wsSender.accept(EventType.UPDATE, updatedMessage);

        return updatedMessage;
    }

    // mapping, to what will be came messages
//    @MessageMapping("/changeMessage")
//    //wohin we will put a messages (channel)
//    @SendTo("/topic/activity")
//    public Message change(Message message){
//        return messageRepo.save(message);
//    }



}