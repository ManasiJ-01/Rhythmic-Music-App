package com.example.tunehub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tunehub.entities.Songs;

public interface SongsRepository  extends JpaRepository<Songs,Integer>
{

	public Songs findByName(String name);
	

	
}
