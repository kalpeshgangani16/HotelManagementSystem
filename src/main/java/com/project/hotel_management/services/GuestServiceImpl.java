package com.project.hotel_management.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.hotel_management.dao.GuestDAO;
import com.project.hotel_management.entities.Guest;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestDAO guestDAO;

    @Override
    public Guest createGuest(Guest guest) {
        return guestDAO.save(guest);
    }

    @Override
    public Guest getGuestById(Long guestId) {
        return guestDAO.findById(guestId)
                .orElseThrow(() -> new RuntimeException("Guest not found with ID: " + guestId));
    }

    @Override
    public List<Guest> getAllGuests() {
        return guestDAO.findAll();
    }

    @Override
    public Guest updateGuest(Long guestId, Guest updatedGuest) {
        Guest existingGuest = guestDAO.findById(guestId)
                .orElseThrow(() -> new RuntimeException("Guest not found with ID: " + guestId));

        if (updatedGuest.getFirstName() != null) {
            existingGuest.setFirstName(updatedGuest.getFirstName());
        }
        if (updatedGuest.getLastName() != null) {
            existingGuest.setLastName(updatedGuest.getLastName());
        }
        if (updatedGuest.getEmail() != null) {
            existingGuest.setEmail(updatedGuest.getEmail());
        }
        if (updatedGuest.getPhoneNumber() != null) {
            existingGuest.setPhoneNumber(updatedGuest.getPhoneNumber());
        }

        return guestDAO.save(existingGuest);
    }

    @Override
    public void deleteGuest(Long guestId) {
        Guest guest = guestDAO.findById(guestId)
                .orElseThrow(() -> new RuntimeException("Guest not found with ID: " + guestId));
        guestDAO.delete(guest);
    }
}
