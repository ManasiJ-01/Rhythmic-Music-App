package com.example.tunehub.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.tunehub.entities.Songs;
import com.example.tunehub.entities.Users;
import com.example.tunehub.services.SongsService;
import com.example.tunehub.services.UsersService;

import jakarta.persistence.criteria.Path;
import jakarta.servlet.http.HttpSession;



@Controller
public class SongsController 
{
	@Autowired
	UsersService userv;
	
	@Autowired
	SongsService songserv;
	
	@PostMapping("/addsongs")
	public String addSongs(@ModelAttribute Songs song) 
	{
		String name = song.getName();
		boolean status = songserv.songExists(name);
		if(status==false)
		{
			songserv.addSongs(song);
			return "songsuccess";
		}
		else
		{
			return "songfail";
		}
	
	}
	
	@GetMapping("/map-viewsongs")
	public String viewSongs(Model model, String attributeName)
	{
		List<Songs> songslist = songserv.fetchAllSongs();
		
		model.addAttribute("songslist", songslist);
		return "displaysongs";
	}
	
	@GetMapping("/viewsongs")
	public String viewCustomerSongs(Model model)
	{
		boolean primeCustomerStatus=true;
		
		if(primeCustomerStatus==true)
		{
			List<Songs> songslist = songserv.fetchAllSongs();
			model.addAttribute("songslist", songslist);
			return "displaysongs";
		}
		else {
			return "makepayment";
		}
	}
	
//handling song rating updates

	@PostMapping("/addrating")
	
	public ResponseEntity<String>rateSong(@RequestParam String songName,@RequestParam int rating) 
	{
	
		songserv.updateSongRating(songName, rating);
        return ResponseEntity.ok("Song rating updated successfully");
		
}

	
	
	
	
	

	    
} 

	

	
    
	
	
	


