package com.rengu.demo.springcloud.springclouduserservice.service;

import com.rengu.demo.springcloud.springclouduserservice.Utils.ApplicationMessage;
import com.rengu.demo.springcloud.springclouduserservice.entity.RoleEntity;
import com.rengu.demo.springcloud.springclouduserservice.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // 保存用户角色接口
    @CachePut(value = "Role_Cache", key = "#roleEntity.getName()")
    public RoleEntity saveRole(RoleEntity roleEntity) {
        if (StringUtils.isEmpty(roleEntity.getName())) {
            throw new RuntimeException(ApplicationMessage.ROLE_NAME_ARGS_NOT_FOUND);
        }
        if (hasRoleByName(roleEntity.getName())) {
            throw new RuntimeException(ApplicationMessage.ROLE_NAME_EXISTED + roleEntity.getName());
        }
        return roleRepository.save(roleEntity);
    }

    // 检查角色名称是否已存在
    public boolean hasRoleByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return false;
        }
        return roleRepository.existsAllByName(name);
    }

    // 根据角色名称查询角色
    @Cacheable(value = "Role_Cache", key = "#name")
    public RoleEntity getRoleByName(String name) {
        if (!hasRoleByName(name)) {
            throw new RuntimeException(ApplicationMessage.ROLE_NAME_NOT_FOUND + name);
        }
        return roleRepository.findAllByName(name);
    }
}
