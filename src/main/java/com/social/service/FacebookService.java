package com.social.service;

import org.springframework.social.facebook.api.User;

public interface FacebookService {
    public String facebooklogin();

    public String getFacebookAccessToken(String code);

    public User getfacebookUserProfile(String accessToken);
}