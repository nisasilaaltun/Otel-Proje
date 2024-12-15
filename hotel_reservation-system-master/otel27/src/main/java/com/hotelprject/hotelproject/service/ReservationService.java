package com.hotelprject.hotelproject.service;

import com.hotelprject.hotelproject.model.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Reservation makeReservation(Long roomId, LocalDate reservationDate, LocalDate endDate, String user);
    List<Reservation> findAllReservations();
    Optional<Reservation> findById(Long reservationId);
    void cancelReservation(Long reservationId);
    void cancelReservation(Long reservationId, String username);
    void cancelReservation(Long reservationId, Long roomId);
}
