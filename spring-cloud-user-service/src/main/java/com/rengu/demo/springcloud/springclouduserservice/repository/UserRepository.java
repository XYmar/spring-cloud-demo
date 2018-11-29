package com.rengu.demo.springcloud.springclouduserservice.repository;

import com.rengu.demo.springcloud.springclouduserservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
}
