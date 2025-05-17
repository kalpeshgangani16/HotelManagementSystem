package com.project.hotel_management.services;

import java.util.List;

import com.project.hotel_management.entities.Guest;

public interface GuestService {
	Guest createGuest(Guest guest);
    Guest getGuestById(Long id);
    List<Guest> getAllGuests();
    Guest updateGuest(Long id, Guest updatedGuest);
    void deleteGuest(Long id);
}
