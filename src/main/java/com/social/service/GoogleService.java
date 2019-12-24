package com.social.service;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.social.google.api.plus.Person;

public interface GoogleService {
    public String googlelogin();
    public String getGoogleAccessToken(String code);

    public Person getGoogleUserProfile(String accessToken);
}