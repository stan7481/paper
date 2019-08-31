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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageRepo messageRepo;

    @Autowired
    public MessageController(MessageRepo messageRepo){
        this.messageRepo = messageRepo;
    }

    private int counter = 4;

    



    @GetMapping
    public List<Message> list() {

        return messageRepo.findAll();

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

//        message.put("id", String.valueOf(counter++));
//
//        messasges.add(message);
//
//        return message;
//    }


//    private Map<String, String> getMessage(@PathVariable String id) {
//
//        return messasges.stream()
//            .filter(message -> message.get("id").equals(id))
//            .findFirst()
//            .orElseThrow(NotFoundException::new);
//    }

//    @PutMapping("{id}")
//    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message){
//        Map<String, String> messageFromDb = getMessage(id);
//
//        messageFromDb.putAll(message);
//        messageFromDb.put("id", id);
//
//        return messageFromDb;
//    }
//
//    @DeleteMapping("{id}")
//    public void delete(@PathVariable String id){
//        Map<String,String> message = getMessage(id);
//
//        messasges.remove(message);
//    }

}