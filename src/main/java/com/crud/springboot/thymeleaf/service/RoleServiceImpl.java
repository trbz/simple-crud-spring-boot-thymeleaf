package com.crud.springboot.thymeleaf.service;

import com.crud.springboot.thymeleaf.dao.RoleRepository;
import com.crud.springboot.thymeleaf.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
