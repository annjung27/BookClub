package com.annie.bookclub.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.annie.bookclub.models.LoginUser;
import com.annie.bookclub.models.User;
import com.annie.bookclub.services.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
// ------------------ Display: Render Login and Registration Form Page---------------------
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}
	
// ----------------------Action: Register New User------------------------------------------------
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser,
							BindingResult result, Model model, HttpSession session) {

		// execute the Service to Register FIRST!
		userService.register(newUser, result);
		
		// then check for errors
		if(result.hasErrors()) {
			
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		} else {
			session.setAttribute("user_id", newUser.getId());
			return "redirect:/books";
			}		
		
	}
// ---------------- Action: Login User----------------------------------------------------
	
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin,
							BindingResult result, Model model, HttpSession session) {
		User user = userService.login(newLogin, result);
		
		
		if(result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		} else {
			session.setAttribute("user_id", user.getId());
			return "redirect:/books";
		}			
		
	}	

//-------------------Logout------------------------------------------------------------
	@GetMapping("/logout")
	public String logout(HttpSession s) {
		s.invalidate();
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
