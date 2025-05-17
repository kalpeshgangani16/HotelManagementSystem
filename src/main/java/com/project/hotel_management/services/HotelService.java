package com.project.hotel_management.services;

import java.util.List;

import com.project.hotel_management.entities.Hotel;

public interface HotelService {
	Hotel createHotel(Hotel hotel);
    Hotel getHotelById(Long id);
    List<Hotel> getAllHotels();
    Hotel updateHotel(Long id, Hotel updatedHotel);
    void deleteHotelById(Long id);
}
