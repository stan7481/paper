package com.news.paper.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.news.paper.DB.User;
import com.news.paper.DB.Views;
import com.news.paper.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("userProfile")
public class UserProfileController {
    private  final ProfileService profileService;

    @Autowired
    public UserProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("{id}")
    @JsonView(Views.FullProfile.class)
    public User get(@PathVariable("id") User user){
        return user;
    }

    @PostMapping("change-subscription/{channelId}")
    @JsonView(Views.FullProfile.class)
    public User changeSubscription(
            @AuthenticationPrincipal User subscriber,
            @PathVariable("channelId") User channel
    ){
        if(subscriber.equals(channel)) {
            return channel;
        }
        else {
            return profileService.changeSubscription(channel, subscriber);
        }
    }

}
