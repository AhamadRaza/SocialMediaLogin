package com.social.service;

import com.social.model.UserInfo;

public interface UserService {
    public UserInfo save(UserInfo userInfo);

    public UserInfo findByEmail(String email);

    void update(UserInfo dbUser);
}