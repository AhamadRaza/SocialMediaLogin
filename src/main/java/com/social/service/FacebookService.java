package com.social.service;

import org.springframework.social.facebook.api.User;

public interface FacebookService {
    String facebooklogin();

    String getFacebookAccessToken(String code);

    User getfacebookUserProfile(String accessToken);
}