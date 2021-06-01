package com.crud.springboot.thymeleaf.controller;

import com.crud.springboot.thymeleaf.entity.Role;
import com.crud.springboot.thymeleaf.entity.User;
import com.crud.springboot.thymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String listUsers(Model theModel){

        List <User> theUsers = userService.findAll();

        theModel.addAttribute("users", theUsers);

        return "employees/list-users";
    }

}
