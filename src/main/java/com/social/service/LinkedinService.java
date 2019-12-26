package com.social.service;

import org.springframework.social.facebook.api.User;
import org.springframework.social.linkedin.api.LinkedInProfileFull;

public interface LinkedinService {
    String linkedinlogin();

    String getLinkedAccessToken(String code);

    LinkedInProfileFull getLinkedinUserProfile(String accessToken);
}