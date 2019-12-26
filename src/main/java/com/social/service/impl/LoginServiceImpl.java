package com.social.service.impl;

import com.social.model.UserInfo;
import com.social.repository.UserRepository;
import com.social.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Transactional
public class LoginServiceImpl implements LoginService{
    @Autowired private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserInfo userInfo = userRepository.findByEmailAndEnabled(email,true);
        if(userInfo==null){
            throw new UsernameNotFoundException("user not found"+email);
        }
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+userInfo.getRole());
        UserDetails userDetails = (UserDetails) new User(userInfo.getEmail(),userInfo.getPassword(), Arrays.asList(grantedAuthority));
        return userDetails;
    }
}