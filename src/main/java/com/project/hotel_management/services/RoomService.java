package com.project.hotel_management.services;

import java.util.List;


import com.project.hotel_management.entities.Room;

public interface RoomService {
	Room createRoom(Room room);
    Room getRoomById(Long id);
    List<Room> getAllRooms();
    Room updateRoom(Long id, Room updatedRoom);
    void deleteRoom(Long id);
}
