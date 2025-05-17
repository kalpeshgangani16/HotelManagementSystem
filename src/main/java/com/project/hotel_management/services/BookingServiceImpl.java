package com.project.hotel_management.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.hotel_management.dao.BookingDAO;
import com.project.hotel_management.entities.Booking;
import com.project.hotel_management.entities.Guest;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDAO bookingDAO;

    @Override
    public List<Booking> getAllBookings() {
        return bookingDAO.findAll();
    }

    @Override
    public Booking createBooking1(Booking booking) {
        return bookingDAO.save(booking);
    }
    @Override
    public Booking createBooking2(Booking booking) {
        return bookingDAO.save(booking);
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + id));
    }

    @Override
    public void deleteBookingById(Long bookingId) {
    	Booking booking = bookingDAO.findById(bookingId)
                .orElseThrow(() -> new RuntimeException( "booking not found with ID: " + bookingId));
        bookingDAO.delete(booking);
    }

    @Override
    public Booking updateBooking(Long id, Booking updatedBooking) {
        Booking existingBooking = getBookingById(id);

        if (updatedBooking.getCheckInDate() != null) {
            existingBooking.setCheckInDate(updatedBooking.getCheckInDate());
        }
        if (updatedBooking.getCheckOutDate() != null) {
            existingBooking.setCheckOutDate(updatedBooking.getCheckOutDate());
        }
        if (updatedBooking.getGuest() != null) {
            existingBooking.setGuest(updatedBooking.getGuest());
        }
        if (updatedBooking.getRoom() != null) {
            existingBooking.setRoom(updatedBooking.getRoom());
        }

        return bookingDAO.save(existingBooking);
    }
    
    
    @Override
    public List<Booking> getBookingsByGuestFirstName(String firstName) {
        List<Booking> bookings = bookingDAO.findByGuest_FirstName(firstName);
        
        if (bookings.isEmpty()) {
            throw new RuntimeException("bookings not found for firstname: " + firstName);
        }
        
        return bookings;
    }

    @Override
    public boolean isRoomAvailable(Long roomId, LocalDate checkInDate, LocalDate checkOutDate) {
        List<Booking> existingBookings = bookingDAO.findConflictingBookings(roomId, checkInDate, checkOutDate);
        return existingBookings.isEmpty(); // If no booking at same date
    }
    
    
    
}
