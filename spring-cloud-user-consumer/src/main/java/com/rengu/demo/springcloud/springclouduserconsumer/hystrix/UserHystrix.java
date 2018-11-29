package com.rengu.demo.springcloud.springclouduserconsumer.hystrix;

import com.rengu.demo.springcloud.springclouduserconsumer.feignclient.UserFeignClient;
import org.springframework.stereotype.Component;

@Component
public class UserHystrix implements UserFeignClient {
}
