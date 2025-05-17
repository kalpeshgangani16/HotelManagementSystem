package com.project.hotel_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.hotel_management.entities.Guest;
import com.project.hotel_management.services.GuestService;

@RestController
@RequestMapping("/api/guests")
public class GuestController {
	
	@Autowired
	private GuestService guestService;
	
	@PostMapping
    public ResponseEntity<?> createGuest(@RequestBody Guest guest) {
        Guest createdGuest = guestService.createGuest(guest);
        return ResponseEntity.status(HttpStatus.CREATED).body("guest created successfully.");
    }

    @GetMapping
    public ResponseEntity<?> getAllGuests() {
        List<Guest> guests = guestService.getAllGuests();
        if(guests.isEmpty()) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND)
            		.body("No any guest.");
        }
        
        return ResponseEntity.ok(guests);
    }

    @GetMapping("/{guestId}")
    public ResponseEntity<?> getGuestById(@PathVariable Long guestId) {
    	try {
    		Guest guest = guestService.getGuestById(guestId);
    		return ResponseEntity.ok(guest);
    	} catch (RuntimeException e) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND)
           		 .body("Guest not found with ID " + guestId);
    	}
    }

    @PutMapping("/{guestId}")
    public ResponseEntity<?> updateGuest(@PathVariable Long guestId, @RequestBody Guest guest) {
        Guest updatedGuest = guestService.updateGuest(guestId, guest);
//        return ResponseEntity.ok(updatedGuest);
        return ResponseEntity.status(HttpStatus.OK)
        		.body("updated successfully.");
    }

    @DeleteMapping("/{guestId}")
    public ResponseEntity<?> deleteGuest(@PathVariable Long guestId) {
    	try {
    		guestService.deleteGuest(guestId);
    		return new ResponseEntity<>(HttpStatus.OK);
    	} catch (RuntimeException e) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND)
    				.body("Guest not found with ID " + guestId);
    	}
    }
}
