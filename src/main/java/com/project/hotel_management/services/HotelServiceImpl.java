package com.project.hotel_management.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hotel_management.dao.HotelDAO;
import com.project.hotel_management.entities.Guest;
import com.project.hotel_management.entities.Hotel;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelDAO hotelDAO;

//    @Override
//    public Hotel createHotel(Hotel hotel) {
//        return hotelDAO.save(hotel);
//    }
    @Override
    public Hotel createHotel(Hotel hotel) {
        if (hotelDAO.count() >= 1) {
            throw new RuntimeException("Only one hotel is allowed in the system.");
        }
        return hotelDAO.save(hotel);
    }


    @Override
    public Hotel getHotelById(Long hotelId) {
        return hotelDAO.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found with ID: " + hotelId));
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelDAO.findAll();
    }

    @Override
    public Hotel updateHotel(Long hotelId, Hotel updatedHotel) {
        Hotel existingHotel = getHotelById(hotelId);

        if (updatedHotel.getName() != null) {
            existingHotel.setName(updatedHotel.getName());
        }
        if (updatedHotel.getAddress() != null) {
            existingHotel.setAddress(updatedHotel.getAddress());
        }
        if (updatedHotel.getCity() != null) {
            existingHotel.setCity(updatedHotel.getCity());
        }
        if (updatedHotel.getPhoneNumber() != null) {
            existingHotel.setPhoneNumber(updatedHotel.getPhoneNumber());
        }

        return hotelDAO.save(existingHotel);
        		
    }

    @Override
    public void deleteHotelById(Long hotelId) {
    	Hotel hotel = hotelDAO.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Guest not found with ID: " + hotelId));
        hotelDAO.delete(hotel);
    }
}
