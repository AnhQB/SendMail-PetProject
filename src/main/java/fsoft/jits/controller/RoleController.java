package fsoft.jits.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fsoft.jits.mybatis.model.Role;
import fsoft.jits.service.RoleService;

@Controller
@RequestMapping("/manager/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String findAll(Model model) {
		List<Role> listRole = roleService.findAll();
		model.addAttribute("listRole", listRole);
		return "role/index";
	}
	
	@GetMapping("/create-update")
	public String createUpdate(@RequestParam(required = false) String id) {
		if(id != null && id != "") { // update
			//search id
		}
		
		return "role/create_update";
	}
	
	@PostMapping("/save")
	//@PreAuthorize("hasRole('USER')")
	public String save(@ModelAttribute Role role) {
		if(role.getRoleId() == 0) {
			roleService.insert(role);
		}
		return "redirect:/manager/role/";
	}
}
