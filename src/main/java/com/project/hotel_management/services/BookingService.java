package com.project.hotel_management.services;

import java.time.LocalDate;
import java.util.List;

import com.project.hotel_management.entities.Booking;

public interface BookingService {

	public Booking createBooking1(Booking booking);
	public Booking createBooking2(Booking booking);
	public List<Booking> getAllBookings();
	Booking getBookingById(Long id);
    void deleteBookingById(Long id);
    Booking updateBooking(Long id,Booking booking);
    List<Booking> getBookingsByGuestFirstName(String firstName);
    
    public boolean isRoomAvailable(Long roomId, LocalDate checkInDate, LocalDate checkOutDate);

}
