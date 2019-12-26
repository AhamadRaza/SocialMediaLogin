package com.social.controller;

import com.social.model.UserInfo;
import com.social.service.LinkedinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LinkedinController {
    @Autowired
    private LinkedinService linkedinService;

    @GetMapping(value = "/linkedinlogin")
    public RedirectView linkedinlogin(){
        RedirectView redirectView = new RedirectView();
        String url = linkedinService.linkedinlogin();
        System.out.println(url);
        redirectView.setUrl(url);
        return redirectView;
    }

    @GetMapping(value = "/linkedin")
    public String linkedin(@RequestParam("code") String code){
        String accessToken = linkedinService.getLinkedAccessToken(code);
        return "redirect:/linkedinprofiledata/"+accessToken;
    }

    @GetMapping(value = "/linkedinprofiledata/{accessToken:.}")
    public String linkedinprofiledata(@PathVariable String accessToken, Model model){
        LinkedInProfileFull profileFull = linkedinService.getLinkedinUserProfile(accessToken);
        UserInfo userInfo  = new UserInfo(profileFull.getFirstName(), profileFull.getLastName(), profileFull.getProfilePictureUrl());
        model.addAttribute("user", userInfo);
        return "view/userprofile";
    }
}