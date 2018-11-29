package com.rengu.demo.springcloud.springclouduserconsumer.feignclient;

import com.rengu.demo.springcloud.springclouduserconsumer.hystrix.UserHystrix;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "spring-cloud-user-service", fallback = UserHystrix.class)
public interface UserFeignClient {
}
