package com.news.paper.controller;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.paper.DB.User;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class Content {

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    public class User {
        public int id;
        public String name;

        public List<Item> userItems;

        public List<User> subscribers;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
            userItems = new ArrayList<Item>();
            subscribers = new ArrayList<User>();
        }
        public void addItem(Item usrItem) {
            userItems.add(usrItem);
        }



        public void addSubscribers(User user) { subscribers.add(user); }



    }

    public class Item {
        public int id;
        public String itemName;
        public User owner;

        public Item(int id, String itemName, User owner) {
            this.id = id;
            this.itemName = itemName;
            this.owner = owner;
        }
    }


    @GetMapping("content1")
    public String test() throws JsonProcessingException {

        User user1 = new User(1, "John");
        User user2 = new User(2, "Steve");
        User user3 = new User(3, "Stan");


        user1.addSubscribers(user2);
        user1.addSubscribers(user3);

        user2.addSubscribers(user1);
        user2.addSubscribers(user3);

        Item item = new Item(2, "book", user1);

      String str =  new ObjectMapper().writeValueAsString(user1);

        return "content1";
    }






    @GetMapping("content2")
    public String test2(){




        return "content2";
    }

}
