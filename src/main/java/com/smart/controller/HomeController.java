package com.smart.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.entities.User;
import com.smart.helper.Message;
import com.smart.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class HomeController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/")
	public String handler(Model model) {
		model.addAttribute("title","smart contact manager");
		return "home";
	}
	
	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("about","about-smart contact manager");
		return "about";
	}
	
	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("register","about-smart contact manager");
		model.addAttribute("newUser",new User());
		return "signup";
	}
	
	@PostMapping("/do_register")
	public String register(@Valid @ModelAttribute("newUser") User user,BindingResult result,@RequestParam(value="agreement",defaultValue = "false") boolean agreement,Model model,HttpSession session) {
		
	try {
		if(!agreement) {
//			System.out.println("you have not agreed the terms and condition");
			throw new Exception("you have not agreed the terms and condition");
		}
		
		if(result.hasErrors()) {
			return "signup";
		}
		user.setRole("Role_User");
		user.setEnabled(true);
		user.setImageUrl("default.png");
		
		User user1 = userRepository.save(user);		
		model.addAttribute("user",new User());
		session.setAttribute("message",new Message("Successfully Registered","alert-success"));
		System.out.println(agreement);
		System.out.println(user1);
		
		return "signup";
		
	} catch (Exception e) {
		e.printStackTrace();
		model.addAttribute("user",user);
		session.setAttribute("message",new Message("something went wrong!!"+e.getMessage(),"alert-danger"));
		return "signup";
	}
		
	}
	
}
