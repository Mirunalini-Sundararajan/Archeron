package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;

import com.example.modal.User;
import com.example.services.UserService;

@Controller
public class ApplicationController 
{
	@Autowired
	private UserService userService;	
	
	@RequestMapping("/welcome")
	public String Welcome(HttpServletRequest request)
	{
		request.setAttribute("mode","MODE_HOME");
		return "welcomepage";
	}
	
	@RequestMapping("/register")
	public String registration(HttpServletRequest request)
	{
		request.setAttribute("mode","MODE_REGISTER");
		return "welcomepage";
	}
	@PostMapping("/save-user")
	public String registerUser(@ModelAttribute User user,BindingResult bindingResult, HttpServletRequest request)
	{
		userService.saveMyUser(user);
		request.setAttribute("mode","MODE_HOME");
		return "welcomepage";
	}
	public String saveuser()
	{
		return "User Saved";
	}
	@RequestMapping("/login")
	public String login(HttpServletRequest request)
	{
		request.setAttribute("mode","MODE_LOGIN");
		return "welcomepage";
	}
	@RequestMapping("/login-user")
	public String loginUser(@ModelAttribute User user,HttpServletRequest request)
	{
		if(userService.findByUsernameAndPassword(user.getUsername(),user.getPassword())!=null)
				return "homepage";
		else
		{
			request.setAttribute("error","Invalid username or password");
			request.setAttribute("mode","MODE-LOGIN");
			return "welcomepage";
		}
	}
}
