package com.crud.springboot.thymeleaf.controller;

import com.crud.springboot.thymeleaf.entity.Role;
import com.crud.springboot.thymeleaf.entity.User;
import com.crud.springboot.thymeleaf.service.RoleService;
import com.crud.springboot.thymeleaf.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    // == show list of users ==
    @GetMapping("/list")
    public String listUsers(Model theModel){

        List <User> theUsers = userService.findAll();

        theModel.addAttribute("users", theUsers);

        return "employees/list-users";
    }

    // == delete employee ==
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("userId") int theId) {

        userService.deleteById(theId);

        return "redirect:/users/list";
    }

    // == save user ==
    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User theUser) {

        userService.savePermissions(theUser);

        // use redirect to prevent duplicate submissions
        return "redirect:/users/list";
    }

    // == show update form ==
    @GetMapping("/updateForm")
    public String updateForm(@RequestParam("userId") int theId,
                             Model theModel) {

        User theUser = userService.findById(theId);

        List<Role> permissions = roleService.findAll();

        theModel.addAttribute("user", theUser);
        theModel.addAttribute("permissions", permissions);

        return "employees/user-form";
    }
}
