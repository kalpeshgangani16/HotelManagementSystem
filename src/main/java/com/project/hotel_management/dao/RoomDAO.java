package com.project.hotel_management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hotel_management.entities.Room;

public interface RoomDAO extends JpaRepository<Room,Long> {

}
