package com.rengu.demo.springcloud.springclouduserconsumer.controller;

import com.rengu.demo.springcloud.springclouduserconsumer.Utils.ResultUtils;
import com.rengu.demo.springcloud.springclouduserconsumer.entity.ResultEntity;
import com.rengu.demo.springcloud.springclouduserconsumer.entity.UserEntity;
import com.rengu.demo.springcloud.springclouduserconsumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 保存用户信息
    @PostMapping
    public ResultEntity saveUser(UserEntity userEntity) {
        return ResultUtils.build(userService.saveUser(userEntity));
    }
}
