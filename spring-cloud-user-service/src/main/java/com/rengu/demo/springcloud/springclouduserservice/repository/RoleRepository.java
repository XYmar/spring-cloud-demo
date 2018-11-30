package com.rengu.demo.springcloud.springclouduserservice.repository;

import com.rengu.demo.springcloud.springclouduserservice.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {

    boolean existsByName(String name);

    RoleEntity findByName(String name);
}
