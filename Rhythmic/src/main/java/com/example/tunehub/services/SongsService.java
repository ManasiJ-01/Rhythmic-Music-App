package com.example.tunehub.services;

import java.util.List;

import com.example.tunehub.entities.Songs;

public interface SongsService
{
	public String addSongs(Songs song);
	
	public boolean songExists(String name);
	
	public List<Songs> fetchAllSongs();
	
	public String updateSong(Songs song);
	
	void updateSongRating(String songName, int rating);

	Songs getSongByName(String songName);

	
	

}
