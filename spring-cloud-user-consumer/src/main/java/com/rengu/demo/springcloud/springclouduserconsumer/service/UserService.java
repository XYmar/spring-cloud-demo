package com.rengu.demo.springcloud.springclouduserconsumer.service;

import com.rengu.demo.springcloud.springclouduserconsumer.entity.UserEntity;
import com.rengu.demo.springcloud.springclouduserconsumer.feignclient.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    private final UserFeignClient userFeignClient;

    @Autowired
    public UserService(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    // 保存用户信息
    public UserEntity saveUser(UserEntity userEntity) {
        return userFeignClient.saveUser(userEntity);
    }
}
