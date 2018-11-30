package com.rengu.demo.springcloud.springclouduserservice.controller;

import com.rengu.demo.springcloud.springclouduserservice.entity.UserEntity;
import com.rengu.demo.springcloud.springclouduserservice.service.RoleService;
import com.rengu.demo.springcloud.springclouduserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final RoleService roleService;

    private final UserService userService;
    @Value("${config.defaultUserRoleName}")
    private String defaultUserRoleName;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping
    public UserEntity saveUser(@RequestBody UserEntity userEntity) {
        return userService.saveUser(userEntity, roleService.getRoleByName(defaultUserRoleName));
    }

    @GetMapping(value = "/by-username")
    public UserEntity getUserByUsername(@RequestParam(value = "username") String username) {
        return userService.getUserByUsername(username);
    }
}
