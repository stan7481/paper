package com.news.paper.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import com.news.paper.DB.Message;
import com.news.paper.exceptions.NotFoundException;

import com.news.paper.repo.MessageRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageRepo messageRepo;

    @Autowired
    public MessageController(MessageRepo messageRepo){
        this.messageRepo = messageRepo;
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
    }

    @PostMapping
    public Message create (@RequestBody Message message){
        message.setCreationDate(LocalDateTime.now());

         return messageRepo.save(message);
    }

    @PutMapping("{id}")
    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message
    )
    {
        BeanUtils.copyProperties(message, messageFromDb, "id");
        return messageRepo.save(messageFromDb);
    }



}