package com.hotelprject.hotelproject.service;

import com.hotelprject.hotelproject.repository.ReservationRepository;
import com.hotelprject.hotelproject.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private RoomServiceImpl roomService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllRooms() {
        // Arrange
        Room room1 = new Room();
        room1.setId(1L);
        room1.setAvailable(true);

        Room room2 = new Room();
        room2.setId(2L);
        room2.setAvailable(true);

        when(roomRepository.findAllByAvailableIsTrue()).thenReturn(List.of(room1, room2));

        // Act
        List<Room> rooms = roomService.getAllRooms();

        // Assert
        assertNotNull(rooms);
        assertEquals(2, rooms.size());
        verify(roomRepository, times(1)).findAllByAvailableIsTrue();
    }

    @Test
    void testGetRoomById_Found() {
        // Arrange
        Long roomId = 1L;
        Room room = new Room();
        room.setId(roomId);

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));

        // Act
        Room foundRoom = roomService.getRoomById(roomId);

        // Assert
        assertNotNull(foundRoom);
        assertEquals(roomId, foundRoom.getId());
        verify(roomRepository, times(1)).findById(roomId);
    }

    @Test
    void testGetRoomById_NotFound() {
        // Arrange
        Long roomId = 1L;
        when(roomRepository.findById(roomId)).thenReturn(Optional.empty());

        // Act
        Room foundRoom = roomService.getRoomById(roomId);

        // Assert
        assertNull(foundRoom);
        verify(roomRepository, times(1)).findById(roomId);
    }

    @Test
    void testSaveRoom() {
        // Arrange
        Room room = new Room();
        room.setAvailable(true);

        when(roomRepository.save(room)).thenReturn(room);

        // Act
        Room savedRoom = roomService.saveRoom(room);

        // Assert
        assertNotNull(savedRoom);
        verify(roomRepository, times(1)).save(room);
    }

    @Test
    void testDeleteRoom() {
        // Arrange
        Long roomId = 1L;

        // Act
        roomService.deleteRoom(roomId);

        // Assert
        verify(roomRepository, times(1)).deleteById(roomId);
    }

    @Test
    void testGetAvailableRooms() {
        // Arrange
        String reservationDate = LocalDate.now().toString();
        LocalDate date = LocalDate.parse(reservationDate);

        Room room1 = new Room();
        room1.setId(1L);
        room1.setAvailable(true);

        Room room2 = new Room();
        room2.setId(2L);
        room2.setAvailable(true);

        Room room3 = new Room();
        room3.setId(3L);
        room3.setAvailable(true);

        // Oda 2 rezervasyon yapılmış olacak
        when(roomRepository.findAll()).thenReturn(List.of(room1, room2, room3));
        when(reservationRepository.findByReservationDate(date)).thenReturn(List.of());

        // Act
        List<Room> availableRooms = roomService.getAvailableRooms(reservationDate);

        // Assert
        assertNotNull(availableRooms);
        assertEquals(3, availableRooms.size()); // Rezervasyon yapılmamış 3 oda olmalı
        verify(roomRepository, times(1)).findAll();
        verify(reservationRepository, times(1)).findByReservationDate(date);
    }
}
