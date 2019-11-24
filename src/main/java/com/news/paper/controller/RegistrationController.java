package com.news.paper.controller;


import com.news.paper.DB.Role;
import com.news.paper.DB.User;
import com.news.paper.repo.UserRepo;
import com.news.paper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userSevice;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model, HttpServletRequest request) {

       User userinDB =userRepo.findByUsername(user.getUsername());

        if (!(user.getPassword().equals(user.getPassword2()))) {
            model.addAttribute("passErr","passwords must be same");
            model.addAttribute("userN",user.getUsername());
            return "registration";
        }

        if (userinDB != null) {
            model.addAttribute("usrNErr","user already exist");
            model.addAttribute("userN",user.getUsername());
            return "registration";
        }
        else {
            String uncodedPass = user.getPassword();
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);

            userSevice.authLogin(user, uncodedPass, request);

            return "redirect:/";

        }

    }

}
