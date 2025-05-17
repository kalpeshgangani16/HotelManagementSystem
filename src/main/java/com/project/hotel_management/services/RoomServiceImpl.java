package com.project.hotel_management.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hotel_management.dao.RoomDAO;
import com.project.hotel_management.entities.Hotel;
import com.project.hotel_management.entities.Room;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {
    
    @Autowired
    private RoomDAO roomDAO;

    @Override
    public Room createRoom(Room room) {
        return roomDAO.save(room);
    }

    @Override
    public Room getRoomById(Long roomId) {
        return roomDAO.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + roomId));
    }

    @Override
    public List<Room> getAllRooms() {
        return roomDAO.findAll();
    }

    @Override
    public Room updateRoom(Long id, Room updatedRoom) {
        Room existingRoom = getRoomById(id);

        if (updatedRoom.getRoomNumber() != null) {
            existingRoom.setRoomNumber(updatedRoom.getRoomNumber());
        }
        if (updatedRoom.getRoomType() != null) {
            existingRoom.setRoomType(updatedRoom.getRoomType());
        }
        if (updatedRoom.getPrice() != null) {
            existingRoom.setPrice(updatedRoom.getPrice());
        }
        existingRoom.setIsAvailable(updatedRoom.isAvailable());

        

        return roomDAO.save(existingRoom);
    }

    @Override
    public void deleteRoom(Long roomId) {
    	Room room = roomDAO.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Guest not found with ID: " + roomId));
        roomDAO.delete(room);
    }
}
