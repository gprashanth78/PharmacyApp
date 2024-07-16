package com.pharmacy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.pharmacy.entity.Role;
import com.pharmacy.entity.UserDto;
import com.pharmacy.service.RoleService;
import com.pharmacy.service.UserService;

@Controller
public class AuthenticationController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		List<Role> roles = roleService.findAllRoles();
		model.addAttribute("user", new UserDto());
		model.addAttribute("roles", roles);
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(UserDto registrationDto) {
		userService.saveUser(registrationDto);
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}
}