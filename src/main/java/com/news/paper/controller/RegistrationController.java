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
//        model.addAttribute("passErr","passwords must be same");
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
            model.addAttribute("usrNErr","user exist");
            model.addAttribute("userN",user.getUsername());
            return "registration";
        }
        else {
            String uncodedPass = user.getPassword();
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);

//            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
//            SecurityContextHolder.getContext().setAuthentication(authentication);


            userSevice.authLogin(user, uncodedPass, request);

//            try {
//                request.login(user.getUsername(),user.getPassword());
//            } catch (ServletException e) {
//                e.printStackTrace();
//            }

//            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
//            request.getSession();
//
//            token.setDetails(new WebAuthenticationDetails(request));
//
//            try{
//                Authentication auth = authenticationManager.authenticate(token);
//
//                SecurityContextHolder.getContext().setAuthentication(auth);
//            } catch(Exception e){
//                e.printStackTrace();
//            }

//            try {
//                authenticateUserAndSetSession(user, user.getPassword(), request);
//            } catch (Exception e) {
//
//                e.printStackTrace();
//            }

//            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), temp);
//            request.getSession();
//
//            token.setDetails(new WebAuthenticationDetails(request));
//
//            try{
//                Authentication auth = authenticationManager.authenticate(token);
//
//                SecurityContextHolder.getContext().setAuthentication(auth);
//            } catch(Exception e){
//                e.printStackTrace();
//            }

            return "redirect:/";


//            return "redirect:/login";
//            return "redirect:/";
//                return "forward:/login";
//        return "/login";
        }

    }



//    private void authenticateUserAndSetSession(User user, String passwordFromForm, HttpServletRequest request){
//
//        String username = user.getUsername();
//        System.out.println("username:  " + username + " password: " + passwordFromForm);
//
//        UserDetails userDetails = userSevice.loadUserByUsername(user.getUsername());
//
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, passwordFromForm, userDetails.getAuthorities());
//        request.getSession();
//
//        System.out.println("Line Authentication 1");
//
//        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
//
//        System.out.println("Line Authentication 2");
//
//        Authentication authenticatedUser = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//
//        System.out.println("Line Authentication 3");
//
//
//        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
//            SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
//            System.out.println("Line Authentication 4");
//
//        }
//
//     request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());// creates context for that session.
//
//        System.out.println("Line Authentication 5");
//
//        session.setAttribute("username", user.getUsername());
//
//        System.out.println("Line Authentication 6");
//
//        session.setAttribute("authorities", usernamePasswordAuthenticationToken.getAuthorities());
//
//        System.out.println("username:  " + user.getUsername() + "password: " + user.getPassword()+"authorities: "+ usernamePasswordAuthenticationToken.getAuthorities());
//
//        user = userDAO.validate(user.getUsername(), user.getPassword());
//        log.debug("You are successfully register");
//
//    }


}
