package fsoft.jits.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fsoft.jits.mybatis.model.User;
import fsoft.jits.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
//	@GetMapping("/")
//	public String getHome() {
//		return "home";
//	}
//	@RequestMapping("/users")
//	public String getUsers() {
//		List<User> users = userService.findAll();
//		return "home";
//	}
}
