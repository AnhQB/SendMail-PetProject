package fsoft.jits.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fsoft.jits.mybatis.dto.ChangePassDTO;
import fsoft.jits.mybatis.model.User;
import fsoft.jits.service.UserService;

@Controller
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
	public String welcomePage() {
		return "home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		return "loginPage";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerPage() {
		return "registerPage";
	}
	
	@RequestMapping(value = "/process_register", method = RequestMethod.POST)
	public String processRegister(@ModelAttribute User user, HttpServletRequest request, Model model) {
		userService.register(user, getSiteURL(request));
		model.addAttribute("email", user.getEmail());
		return "confirmMail";
		
	}
	
	@RequestMapping(value = "/verify", method = RequestMethod.GET)
	public String verify(Model model, @RequestParam("verificationCode") String verificationCode) {
		User user = userService.verifyAccount(verificationCode);
		if(user == null) {
			return "redirect:/login";
		}else {
			model.addAttribute("user", user);
			return "changePass";
		}
		
	}
	
	@RequestMapping(value = "/process_changePass", method = RequestMethod.POST)
	public String processChangePass(@ModelAttribute ChangePassDTO chPassDTO) {
		userService.changePassword(chPassDTO);
		return "successChangePass";
	}
	
	@RequestMapping(value = "/not_permission", method = RequestMethod.GET)
	public String notPermission() {
		return "notPermission";
	}
	
	@RequestMapping(value="/perform_logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/"; //You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
	
	private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    } 
	
}
