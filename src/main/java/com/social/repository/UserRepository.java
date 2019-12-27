package com.social.repository;

import com.social.model.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserInfo,Long>{
    UserInfo findByEmailAndEnabled(String email, boolean enabled);

    UserInfo findByEmail(String email);
}