package com.example.tunehub.controller;

import java.util.List;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tunehub.entities.Songs;
import com.example.tunehub.entities.Users;
import com.example.tunehub.services.SongsService;
import com.example.tunehub.services.UsersService;

//import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController 
{
	
	@Autowired
	UsersService userv;
	
	@Autowired
	SongsService songserv;
	
	@PostMapping("/register")
	public String addUser(@ModelAttribute Users user)		
	{
		boolean userStatus = userv.emailExists(user.getEmail());
		if(userStatus == false)
		{
			userv.addUser(user);
			System.out.println("User is added");
			return "registersuccess";
		}
		else
		{
			System.out.println("User is already exist");
			return "registerfail";
		}
		
		
	}
	
	@PostMapping("/login")
	public String validateUser(@RequestParam String email,@RequestParam String password,HttpSession session)
	{
		boolean loginstatus = userv.validateUser(email,password);
		String role = userv.getRole(email);
		
		if(loginstatus == true)
		{
			session.setAttribute("email",email);
			if(role.equals("admin"))
			{
				return "adminhome";
			}
			else {
				return "customerhome";
			}
		}
		else
		{
			return "loginfail";
		}
	}
	
	@GetMapping("/exploreSongs")
	public String exploreSongs(HttpSession session, Model model)
	{
		String email = (String) session.getAttribute("email");
		
		Users user = userv.getUser(email);
		
		boolean userStatus= user.isPremium();
		
		if(userStatus == true)
		{
			List<Songs> songslist = songserv.fetchAllSongs();
			
			model.addAttribute("songslist", songslist);
			return "displaysongs";
		}else {
			return "payment";
		}
	}
	
	
}