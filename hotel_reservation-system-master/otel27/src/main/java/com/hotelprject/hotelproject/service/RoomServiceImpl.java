package com.hotelprject.hotelproject.service;

import com.hotelprject.hotelproject.model.Room;
import com.hotelprject.hotelproject.repository.ReservationRepository;
import com.hotelprject.hotelproject.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    public RoomServiceImpl(RoomRepository roomRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAllByAvailableIsTrue();
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public List<Room> getAvailableRooms(String reservationDate) {

        LocalDate localDate = LocalDate.parse(reservationDate);

        // Rezervasyon yapılmış odaları bul
        List<Long> reservedRoomIds = reservationRepository.findByReservationDate(localDate).stream()
                .map(reservation -> reservation.getRoom().getId())
                .toList();

        // Rezervasyon yapılmamış odaları döndür
        return roomRepository.findAll().stream()
                .filter(room -> !reservedRoomIds.contains(room.getId()))
                .toList();
    }
}

