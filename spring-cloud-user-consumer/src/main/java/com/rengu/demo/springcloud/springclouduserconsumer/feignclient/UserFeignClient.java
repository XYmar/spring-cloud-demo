package com.rengu.demo.springcloud.springclouduserconsumer.feignclient;

import com.rengu.demo.springcloud.springclouduserconsumer.entity.UserEntity;
import com.rengu.demo.springcloud.springclouduserconsumer.hystrix.UserHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "spring-cloud-user-service", fallback = UserHystrix.class)
public interface UserFeignClient {

    @PostMapping(value = "/users")
    UserEntity saveUser(UserEntity userEntity);
}
