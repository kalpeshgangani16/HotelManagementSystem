package com.project.hotel_management.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.hotel_management.entities.Booking;

public interface BookingDAO extends JpaRepository<Booking,Long> {
	List<Booking> findByGuest_FirstName(String firstName);
	
	@Query("SELECT b FROM Booking b WHERE b.room.id = :roomId AND " +
	           "(b.checkInDate < :checkOutDate AND b.checkOutDate > :checkInDate)")
	    List<Booking> findConflictingBookings(@Param("roomId") Long roomId, 
	                                          @Param("checkInDate") LocalDate checkInDate, 
	                                          @Param("checkOutDate") LocalDate checkOutDate);
	
}
