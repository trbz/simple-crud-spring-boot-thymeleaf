package com.crud.springboot.thymeleaf.service;

import com.crud.springboot.thymeleaf.entity.User;
import com.crud.springboot.thymeleaf.user.NewSystemUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(NewSystemUser newSystemUser);

    List<User> findAll();
}
