package com.resources.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.resources.entity.AuthUser;
import com.resources.entity.Customer;
import com.resources.service.CustomerService;
import com.resources.service.RegisterService;

@Controller
public class WelcomeController {


	@Autowired
	private RegisterService registerService;
	
	@GetMapping("/login")
	 public String showloginPage() {
				
		return "login";
	}
	
	@GetMapping("/register")
	public String registerUser(Model theModel) {
		AuthUser auser = new AuthUser();
		theModel.addAttribute("createnewUser", auser);
		return "register";
	}
	
	@PostMapping("/registerUser")
	public String saveUser(@ModelAttribute("createnewUser") AuthUser newuser, Model model) {
		System.out.println(newuser.getName() + " "+ newuser.getEmail());
		registerService.saveUser(newuser);
		model.addAttribute("registersuccess", "Registration Successful!");
		return "register";
	}
	
	@GetMapping("/accessDenied")
	public String showAccessDeniedPage() {
		return "accessdenied";
	}
}
