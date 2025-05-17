package com.project.hotel_management.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.project.hotel_management.entities.Booking;
import com.project.hotel_management.entities.Guest;
import com.project.hotel_management.entities.Hotel;
import com.project.hotel_management.entities.Room;
import com.project.hotel_management.services.BookingService;
import com.project.hotel_management.services.GuestService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private GuestService guestService;
	
	
	//with provide guest id in JSON
	@PostMapping("/with_guest")
	public ResponseEntity<?> createBooking1(@RequestBody Booking booking) {
	    Guest guest = booking.getGuest();

	    if (guest == null || guest.getId() == null) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No guest provided");
	    }
	    
	    
	    try {
	        Guest existingGuest = guestService.getGuestById(guest.getId());
	        booking.setGuest(existingGuest); 
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Guest not found with ID: " + guest.getId());
	    }

	    Room room = booking.getRoom();
	    if (room == null) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room must be selected for booking");
	    }

	    boolean isRoomAvailable = bookingService.isRoomAvailable(room.getId(), booking.getCheckInDate(), booking.getCheckOutDate());
	    if (!isRoomAvailable) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room not available");
	    }

	    Booking createdBooking = bookingService.createBooking1(booking);
	    return ResponseEntity.status(HttpStatus.CREATED).body("Booking created successfully");
	}
	
	//without guest existance
	@PostMapping("/no_guest")
	public ResponseEntity<?> createBooking2(@RequestBody Booking booking) {
	    
	    Room room = booking.getRoom();
	    if (room == null) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room must be selected for booking");
	    }

	    boolean isRoomAvailable = bookingService.isRoomAvailable(room.getId(), booking.getCheckInDate(), booking.getCheckOutDate());
	    if (!isRoomAvailable) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room not available");
	    }
	    
	    // first Check if the guest exists
	    Guest guest = booking.getGuest();
	    if (guest != null && guest.getId() == null) {
	        Guest savedGuest = guestService.createGuest(guest);
	        booking.setGuest(savedGuest);
	    }
	    
	    Booking createdBooking = bookingService.createBooking2(booking);
	    return ResponseEntity.status(HttpStatus.CREATED).body("guest then booking");
	}


	//simple get ALL
//	@GetMapping
//	public ResponseEntity<List<Booking>> getAllBookings() {
//        List<Booking> bookings = bookingService.getAllBookings();
//        return ResponseEntity.ok(bookings);
//        
//    }	
	
	@GetMapping
	public ResponseEntity<List<Map<String, Object>>> getAllBookings() {
	    List<Booking> bookings = bookingService.getAllBookings();
	    
	    // Use LinkedHashMap to maintain insertion order
	    List<Map<String, Object>> op = bookings.stream().map(booking -> {
	        Map<String, Object> bookingData = new LinkedHashMap<>();
	        bookingData.put("bookingId", booking.getId());
	        bookingData.put("checkInDate", booking.getCheckInDate());
	        bookingData.put("checkOutDate", booking.getCheckOutDate());
	        
	        Map<String, Object> roomData = new LinkedHashMap<>();
	        roomData.put("roomId", booking.getRoom().getId());
	        roomData.put("roomNumber", booking.getRoom().getRoomNumber());
	        bookingData.put("room", roomData);
	        
	        Map<String, Object> guestData = new LinkedHashMap<>();
	        guestData.put("firstName", booking.getGuest().getFirstName());
	        bookingData.put("guest", guestData);

	        return bookingData;
	    }).collect(Collectors.toList());

	    return ResponseEntity.ok(op);
	}
	
	@GetMapping("/{bookingId}")
	public ResponseEntity<?> getBookingById(@PathVariable Long bookingId) {
    	try {
    		Booking booking = bookingService.getBookingById(bookingId);
    		return ResponseEntity.ok(booking);
    	} catch (RuntimeException e) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND)
           		 .body("Boooking with ID " + bookingId + " not found");
    	}
    }
	
	@GetMapping("/my/{firstName}")
	public ResponseEntity<?> getBookingsByGuestFirstName(@PathVariable String firstName) {
		try {
			List<Booking> bookings = bookingService.getBookingsByGuestFirstName(firstName);
		    return ResponseEntity.ok(bookings);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
	           		 .body("Boooking with firstname " + firstName + " not found");
		}
	    
	}

	@PutMapping("/{bookingId}")
	public ResponseEntity<?> updateBooking(@PathVariable Long bookingId, @RequestBody Booking booking) {
        Booking updatedBooking = bookingService.updateBooking(bookingId, booking);
        return ResponseEntity.status(HttpStatus.OK)
        		.body("updated successfully.");
    }
	
	@DeleteMapping("/{deleteBookingId}")
	public ResponseEntity<?> deleteBookingById(@PathVariable String deleteBookingId) {
		try {
			this.bookingService.deleteBookingById(Long.parseLong(deleteBookingId));
			return ResponseEntity.status(HttpStatus.OK)
					.body("booking with id "+deleteBookingId+" deleted.");
		}catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND)
    				.body("no booking exist with id "+deleteBookingId);
    	}
	}
}

