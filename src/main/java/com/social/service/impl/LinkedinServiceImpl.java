package com.social.service.impl;

import com.social.service.LinkedinService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.social.linkedin.api.impl.LinkedInTemplate;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
public class LinkedinServiceImpl implements LinkedinService {
    @Value("${spring.social.facebook.app-id}")
    private String linkedinId;
    @Value("${spring.social.facebook.app-secret}")
    private String linkedinSecret;

    private LinkedInConnectionFactory linkedInConnectionFactory(){
        return new LinkedInConnectionFactory(linkedinId, linkedinSecret);
    }

    @Override
    public String linkedinlogin() {
        OAuth2Parameters parameters = new OAuth2Parameters();
        parameters.setRedirectUri("http://localhost:8084/linkedin");
        parameters.setScope("r_basicprofile,r_emailaddress");
        return linkedInConnectionFactory().getOAuthOperations().buildAuthenticateUrl(parameters);
    }

    @Override
    public String getLinkedAccessToken(String code) {
        return linkedInConnectionFactory().getOAuthOperations().exchangeForAccess(code, "http://localhost:8084/linkedin", null).getAccessToken();
    }

    @Override
    public LinkedInProfileFull getLinkedinUserProfile(String accessToken) {
        LinkedIn linkedIn = new LinkedInTemplate(accessToken);
        LinkedInProfileFull profileFull = linkedIn.profileOperations().getUserProfileFull();
        return profileFull;
    }
}