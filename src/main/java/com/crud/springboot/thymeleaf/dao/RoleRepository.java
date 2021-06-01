package com.crud.springboot.thymeleaf.dao;


import com.crud.springboot.thymeleaf.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleByName(String name);
}
