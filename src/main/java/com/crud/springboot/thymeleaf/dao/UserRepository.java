package com.crud.springboot.thymeleaf.dao;

import com.crud.springboot.thymeleaf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserName(String userName);

}

