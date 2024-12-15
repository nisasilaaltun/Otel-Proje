package com.hotelprject.hotelproject.service;

import com.hotelprject.hotelproject.model.Reservation;
import com.hotelprject.hotelproject.model.Room;
import com.hotelprject.hotelproject.repository.ReservationRepository;
import com.hotelprject.hotelproject.repository.RoomRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    @Override
    public Reservation makeReservation(Long roomId, LocalDate reservationDate, LocalDate endDate, String user) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + roomId));

        // Seçilen tarihler için rezervasyon kontrolü
        List<Reservation> existingReservations = reservationRepository.findByRoomAndDateRange(room, reservationDate, endDate);
        if (!existingReservations.isEmpty()) {
            throw new RuntimeException("Bu tarihler için oda zaten rezerve edilmiş!");
        }

        // Yeni rezervasyon oluştur
        Reservation reservation = Reservation.builder()
                .room(room)
                .reservationDate(reservationDate)
                .endDate(endDate)
                .userInfo(user)
                .status("ONAYLANDI")
                .build();

        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findById(Long reservationId) {
        return reservationRepository.findById(reservationId);
    }

    @Override
    public void cancelReservation(Long reservationId, String username) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Rezervasyon bulunamadı"));

        // Kullanıcı kontrolü
        if (!reservation.getUserInfo().equals(username)) {
            throw new RuntimeException("Bu rezervasyonu iptal etme yetkiniz yok");
        }

        cancelReservationProcess(reservation);
    }

    @Override
    public void cancelReservation(Long reservationId, Long roomId) {

    }

    @Override
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Rezervasyon bulunamadı"));
        cancelReservationProcess(reservation);
    }

    // Yardımcı metod - rezervasyon iptal işlemleri
    private void cancelReservationProcess(Reservation reservation) {
        reservation.setStatus("İPTAL EDİLDİ");
        reservationRepository.save(reservation);
    }
}