package com.rengu.demo.springcloud.springclouduserconsumer.feignclient;

import com.rengu.demo.springcloud.springclouduserconsumer.entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "spring-cloud-user-service")
public interface UserFeignClient {

    @PostMapping(value = "/users")
    UserEntity saveUser(UserEntity userEntity);

    @GetMapping(value = "/users/{userId}")
    UserEntity getUserById(@PathVariable(value = "userId") String userId);
}
