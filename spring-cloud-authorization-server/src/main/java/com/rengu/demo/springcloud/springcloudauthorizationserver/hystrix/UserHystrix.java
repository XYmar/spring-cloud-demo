package com.rengu.demo.springcloud.springcloudauthorizationserver.hystrix;

import com.rengu.demo.springcloud.springcloudauthorizationserver.entity.UserEntity;
import com.rengu.demo.springcloud.springcloudauthorizationserver.feignclient.UserFeignClient;
import org.springframework.stereotype.Component;

@Component
public class UserHystrix implements UserFeignClient {

    @Override
    public UserEntity getUserByUsername(String username) {
        throw new RuntimeException("根据用户名获取用户失败，查询用户名：" + username);
    }
}
