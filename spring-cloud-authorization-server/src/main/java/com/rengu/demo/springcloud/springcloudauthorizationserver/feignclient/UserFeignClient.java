package com.rengu.demo.springcloud.springcloudauthorizationserver.feignclient;

import com.rengu.demo.springcloud.springcloudauthorizationserver.entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "spring-cloud-user-service")
public interface UserFeignClient {

    @GetMapping(value = "/users/by-username")
    UserEntity getUserByUsername(@RequestParam(value = "username") String username);
}
