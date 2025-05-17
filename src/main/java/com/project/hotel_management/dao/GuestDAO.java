package com.project.hotel_management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hotel_management.entities.Guest;

public interface GuestDAO extends JpaRepository<Guest,Long>{
	
}
