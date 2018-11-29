package com.rengu.demo.springcloud.springcloudauthorizationserver.service;


import com.rengu.demo.springcloud.springcloudauthorizationserver.entity.UserEntity;
import com.rengu.demo.springcloud.springcloudauthorizationserver.feignclient.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    private final UserFeignClient userFeignClient;

    @Autowired
    public UserService(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return getUserByUsername(s);
    }

    public UserEntity getUserByUsername(String username) {
        return userFeignClient.getUserByUsername(username);
    }
}
