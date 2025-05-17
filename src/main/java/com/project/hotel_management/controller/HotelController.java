package com.project.hotel_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.hotel_management.entities.Hotel;
import com.project.hotel_management.services.HotelService;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<?> createHotel(@RequestBody Hotel hotel) {
    	try {
    		Hotel createdHotel = hotelService.createHotel(hotel);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdHotel);
    	}catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
    				.body("only one hotel can be created.");
    	} 
    }

    @GetMapping
    public ResponseEntity<?> getAllHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        if(hotels.isEmpty()) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND)
    				.body("NO any hotel.");
        }
        return ResponseEntity.ok(hotels);
    }

    //Not needed because we restricted hotel no. to 1
//    @GetMapping("/{hotelId}")
//    public ResponseEntity<?> getHotelById(@PathVariable Long hotelId) {
//        try {
//            Hotel hotel = hotelService.getHotelById(hotelId);
//            return ResponseEntity.ok(hotel);
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("Hotel with ID " + hotelId + " not found");
//        }
//    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<?> updateHotel(@PathVariable Long hotelId, @RequestBody Hotel updatedHotel) {
    	try {
    		Hotel hotel = hotelService.updateHotel(hotelId, updatedHotel);
            return ResponseEntity.ok(hotel);
    	}catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hotel exists with ID: " + hotelId);
    	}
        
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<?> deleteHotelById(@PathVariable Long hotelId) {
        try {
            hotelService.deleteHotelById(hotelId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hotel exists with ID: " + hotelId);
        }
    }
    
}
