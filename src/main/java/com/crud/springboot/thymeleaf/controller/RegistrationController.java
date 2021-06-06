package com.crud.springboot.thymeleaf.controller;

import com.crud.springboot.thymeleaf.entity.User;
import com.crud.springboot.thymeleaf.service.UserService;
import com.crud.springboot.thymeleaf.user.NewSystemUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    //  == show registration form  ==
    @GetMapping("/showRegistrationForm")
    public String showRegistrationForm(Model theModel){

        theModel.addAttribute("newSystemUser", new NewSystemUser());

        return "registration/registration-form";
    }

    // == process registration form and show confirmation if success ==
    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("newSystemUser") NewSystemUser newSystemUser,
            BindingResult theBindingResult,
            Model theModel) {

        String userName = newSystemUser.getUserName();

        // form validation
        if (theBindingResult.hasErrors()){
            return "registration/registration-form";
        }

        // check the database if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null){
            theModel.addAttribute("newSystemUser", new NewSystemUser());
            theModel.addAttribute("registrationError", "User name already exists.");

            return "registration/registration-form";
        }

        // create user account
        userService.save(newSystemUser);

        return "registration/registration-confirmation";
    }
}
