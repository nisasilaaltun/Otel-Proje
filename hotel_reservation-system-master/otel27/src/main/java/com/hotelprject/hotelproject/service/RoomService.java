package com.hotelprject.hotelproject.service;
import com.hotelprject.hotelproject.model.Room;
import java.time.LocalDate;
import java.util.List;
public interface RoomService {
    List<Room> getAllRooms();
    Room getRoomById(Long id);
    Room saveRoom(Room room);
    void deleteRoom(Long id);
    List<Room> getAvailableRooms(String reservationDate);
}