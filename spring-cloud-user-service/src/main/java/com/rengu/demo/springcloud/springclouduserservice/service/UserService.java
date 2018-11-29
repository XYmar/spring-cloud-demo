package com.rengu.demo.springcloud.springclouduserservice.service;

import com.rengu.demo.springcloud.springclouduserservice.Utils.ApplicationMessage;
import com.rengu.demo.springcloud.springclouduserservice.entity.RoleEntity;
import com.rengu.demo.springcloud.springclouduserservice.entity.UserEntity;
import com.rengu.demo.springcloud.springclouduserservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 保存管理员用户
    public UserEntity saveUser(UserEntity userEntity, RoleEntity... roleEntities) {
        Set<RoleEntity> roleEntitySet = userEntity.getRoleEntities();
        if (roleEntitySet != null) {
            roleEntitySet.addAll(Arrays.asList(roleEntities));
        } else {
            roleEntitySet = new HashSet<>(Arrays.asList(roleEntities));
        }
        return saveUser(userEntity, roleEntitySet);
    }

    // 保存用户
    @CachePut(value = "User_Cache", key = "#userEntity.getId()")
    public UserEntity saveUser(UserEntity userEntity, Set<RoleEntity> roleEntitySet) {
        if (StringUtils.isEmpty(userEntity.getUsername())) {
            throw new RuntimeException(ApplicationMessage.USER_USERNAME_ARGS_NOT_FOUND);
        }
        if (StringUtils.isEmpty(userEntity.getPassword())) {
            throw new RuntimeException(ApplicationMessage.USER_PASSWORD_ARGS_NOT_FOUND);
        }
        if (hasUserByUsername(userEntity.getUsername())) {
            throw new RuntimeException(ApplicationMessage.USER_USERNAME_EXISTED + userEntity.getUsername());
        }
        userEntity.setPassword(new BCryptPasswordEncoder().encode(userEntity.getPassword()));
        userEntity.setRoleEntities(roleEntitySet);
        return userRepository.save(userEntity);
    }

    // 根据用户名查询用户
    public UserEntity getUserByUsername(String username) {
        if (!hasUserByUsername(username)) {
            throw new RuntimeException(ApplicationMessage.USER_USERNAME_NOT_FOUND + username);
        }
        return userRepository.findAllByUsername(username);
    }

    // 根据用户名查询用户是否存在
    public boolean hasUserByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return false;
        }
        return userRepository.existsAllByUsername(username);
    }
}
