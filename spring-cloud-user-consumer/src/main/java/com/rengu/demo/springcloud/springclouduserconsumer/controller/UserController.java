package com.rengu.demo.springcloud.springclouduserconsumer.controller;

import com.rengu.demo.springcloud.springclouduserconsumer.Utils.ResultUtils;
import com.rengu.demo.springcloud.springclouduserconsumer.entity.ResultEntity;
import com.rengu.demo.springcloud.springclouduserconsumer.entity.UserEntity;
import com.rengu.demo.springcloud.springclouduserconsumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    // 根据Id查询用户
    @GetMapping(value = "/{userId}")
    public ResultEntity getUserById(@PathVariable(value = "userId") String userId) {
        return ResultUtils.build(userService.getUserById(userId));
    }
}
