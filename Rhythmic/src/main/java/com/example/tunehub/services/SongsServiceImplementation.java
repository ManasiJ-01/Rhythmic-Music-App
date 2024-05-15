package com.example.tunehub.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tunehub.entities.Songs;
import com.example.tunehub.entities.Users;
import com.example.tunehub.repositories.SongsRepository;

@Service
public class SongsServiceImplementation implements SongsService
{
	@Autowired
	SongsRepository srepo;

	@Override
	public String addSongs(Songs song)
	{
		srepo.save(song);
		return "song is added";
	}

	@Override
	public boolean songExists(String name) {
		Songs song = srepo.findByName(name);
		if(song==null)
		{
		return false;
		}
		else
		{
			return true;
		}
		
		}

	@Override
	public List<Songs> fetchAllSongs() 
	{
		List<Songs> songslist = srepo.findAll();
		return songslist;
	}

	@Override
	public String updateSong(Songs song) {
		srepo.save(song);
		return null;
	}

	@Override
	public void updateSongRating(String songName, int rating) 
	{
		Songs song = srepo.findByName(songName);
		
		if(song != null) {
			song.setRating(rating);
			srepo.save(song);
			
		}
		else
		{
			// Handle the case where the song with the given name is not found
			
            throw new RuntimeException("Song not found with name: " + songName);
		}
		
		
	}

	
	@Override
	public Songs getSongByName(String songName) {
	    return srepo.findByName(songName);
	}

	
	
	}




