package com.crud.springboot.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/showLoginPage")
	public String showMyLoginPage() {
		
		return "login/login-page";
		
	}

	@GetMapping("/access-denied")
	public String showAccessDenied() {
		
		return "login/access-denied";
		
	}
}
