package com.rengu.demo.springcloud.springclouduserconsumer.hystrix;

import com.rengu.demo.springcloud.springclouduserconsumer.entity.UserEntity;
import com.rengu.demo.springcloud.springclouduserconsumer.feignclient.UserFeignClient;
import org.springframework.stereotype.Component;

@Component
public class UserHystrix implements UserFeignClient {

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        throw new RuntimeException("调用接口：" + "/users" + "异常");
    }
}
