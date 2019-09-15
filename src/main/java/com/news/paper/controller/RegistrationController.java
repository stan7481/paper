package com.news.paper.controller;


import com.news.paper.DB.Role;
import com.news.paper.DB.User;
import com.news.paper.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
//        model.addAttribute("passErr","passwords must be same");
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {

       User userinDB =userRepo.findByUsername(user.getUsername());

        if (!(user.getPassword().equals(user.getPassword2()))) {
            model.addAttribute("passErr","passwords must be same");
            model.addAttribute("userN",user.getUsername());
            return "registration";
        }

        if (userinDB != null) {
            model.addAttribute("usrNErr","user exist");
            model.addAttribute("userN",user.getUsername());
            return "registration";
        }
        else {
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            userRepo.save(user);

            return "redirect:/index";
        }

    }

}
