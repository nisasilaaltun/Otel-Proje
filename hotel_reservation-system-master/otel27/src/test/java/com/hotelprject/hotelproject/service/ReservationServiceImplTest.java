package com.hotelprject.hotelproject.service;

import com.hotelprject.hotelproject.model.Reservation;
import com.hotelprject.hotelproject.repository.ReservationRepository;
import com.hotelprject.hotelproject.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMakeReservation_Success() {
        // Arrange
        Long roomId = 1L;
        LocalDate reservationDate = LocalDate.now();
        LocalDate endDate = reservationDate.plusDays(3);
        String user = "user@example.com";

        Room room = new Room();
        room.setId(roomId);
        room.setAvailable(true);

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));
        when(reservationRepository.save(any(Reservation.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Reservation reservation = reservationService.makeReservation(roomId, reservationDate, endDate, user);

        // Assert
        assertNotNull(reservation);
        assertEquals(roomId, reservation.getRoom().getId());
        assertEquals(reservationDate, reservation.getReservationDate());
        assertEquals(endDate, reservation.getEndDate());
        assertEquals(user, reservation.getUserInfo());
        assertFalse(room.getAvailable()); // The room should be marked as unavailable
        verify(roomRepository, times(1)).save(room);
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testMakeReservation_RoomNotFound() {
        // Arrange
        Long roomId = 1L;
        LocalDate reservationDate = LocalDate.now();
        LocalDate endDate = reservationDate.plusDays(3);
        String user = "user@example.com";

        when(roomRepository.findById(roomId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            reservationService.makeReservation(roomId, reservationDate, endDate, user);
        });

        assertEquals("Room not found with id: " + roomId, thrown.getMessage());
        verify(roomRepository, times(1)).findById(roomId);
        verify(reservationRepository, times(0)).save(any());
    }
}
