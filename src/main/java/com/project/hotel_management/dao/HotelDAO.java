package com.project.hotel_management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hotel_management.entities.Hotel;

public interface HotelDAO extends JpaRepository<Hotel,Long>{

}
