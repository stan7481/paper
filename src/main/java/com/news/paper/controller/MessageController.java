package com.news.paper.controller;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

import com.news.paper.DB.Message;
import com.news.paper.DB.User;
import com.news.paper.DB.Views;
import com.news.paper.dto.EventType;
import com.news.paper.dto.ObjectType;
import com.news.paper.repo.MessageRepo;
import com.news.paper.service.MessageService;
import com.news.paper.util.WsSender;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageRepo messageRepo;
    private final BiConsumer<EventType, Message> wsSender;

    @Autowired
    private MessageService messageService;

    @Autowired
    public MessageController(MessageRepo messageRepo, WsSender wsSender){
        this.messageRepo = messageRepo;

        this.wsSender = wsSender.getSender(ObjectType.MESSAGE, Views.FullMessage.class);
    }

    @GetMapping
    public List<Message> getAll(){
        return (messageService.cutMessages(messageRepo.findAll()));
//        return messageRepo.findAll();
    }


    @GetMapping("{id}")
    public  Message get(@PathVariable("id") Message message){
        return message;
    }


    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        messageRepo.delete(message);
        wsSender.accept(EventType.REMOVE, message);
    }

    @PostMapping
    public Message create (@RequestBody Message message, @AuthenticationPrincipal User user){
        message.setCreationDate(LocalDateTime.now());

         user.setSubscribers(null);
         user.setSubscriptions(null);
//        user.setSubscriptions(null);
        message.setAuthor(user);

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

        messageFromDb.setCreationDate(LocalDateTime.now());

        Message updatedMessage = messageRepo.save(messageFromDb);
        wsSender.accept(EventType.UPDATE, updatedMessage);

        return updatedMessage;
    }


    @PostMapping("onlyOwnerArticles/{channelId}")
    public List<Message> getOnlyOwnerArticles(
            @PathVariable("channelId") User channel
    ){

//       return messageRepo.findByAuthor(channel);
        return (messageService.cutMessages(messageRepo.findByAuthor(channel)));
    }

    @PostMapping("subscriptionsArticle/{userId}")
    public List<Message> getSubscriptionsArticle(
            @PathVariable("userId") User user
    ){
        return messageService.loadMessageBySubscriber(user);
//        return (messageService.cutMessages(messageRepo.findAll()));
    }
}