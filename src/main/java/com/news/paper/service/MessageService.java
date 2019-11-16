package com.news.paper.service;

import com.news.paper.DB.Message;
import com.news.paper.DB.User;
import com.news.paper.repo.MessageRepo;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class MessageService {
    private  final MessageRepo messageRepo;

    @Autowired
    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }



    public List<Message> loadMessageBySubscriber(User user)
    {

        List<Message> messages = messageRepo.findAll();
        List<Message> mess = new ArrayList<Message>();;

        Set<User> Subsc = user.getSubscriptions();

         var test1 = messages.toArray();
         var test2 = Subsc.toArray();


        for (Message message : messages) {


         if(Subsc.contains(message.getAuthor())){
            mess.add(message);
         }

        }
        return cutMessages(mess);
    }

    public List<Message> cutMessages(List<Message> messages){
        List<Message> mess = new ArrayList<Message>();;

        for(Message message : messages){
            if(message.getText().length() > 25){
                message.setText((message.getText().substring(0,100) + "..."));

            }

            mess.add(message);
            int i = 1;
        }

        return mess;
    }




}
