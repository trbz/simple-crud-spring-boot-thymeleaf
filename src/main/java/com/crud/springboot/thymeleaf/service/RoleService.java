package com.crud.springboot.thymeleaf.service;

import com.crud.springboot.thymeleaf.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();
}
