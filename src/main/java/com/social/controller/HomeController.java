package com.social.controller;

import com.social.model.UserInfo;
import com.social.service.SecurityService;
import com.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;

@Controller
public class HomeController {
    @Autowired private UserService userService;
    @Autowired private SecurityService securityService;

    @GetMapping(value = "/")
    public String home(){
        return "view/home";
    }

    @PostMapping(value = "/register")
    public String register(@ModelAttribute UserInfo userInfo, HttpServletRequest request, Model model){
        String password = userInfo.getPassword();
        UserInfo dbUser = userService.save(userInfo);
        securityService.autoLogin(dbUser.getEmail(),password,dbUser.getRole(), request);
        model.addAttribute("user", dbUser);
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = grantedAuthorities.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println(name);
        return "view/userprofile";
    }
}