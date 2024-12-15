package com.hotelprject.hotelproject.repository;
import com.hotelprject.hotelproject.model.Reservation;
import com.hotelprject.hotelproject.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByReservationDate(LocalDate reservationDate);
    Optional<Reservation> findByRoomAndReservationDate(Room room, LocalDate reservationDate);

    @Query("SELECT r FROM Reservation r WHERE r.room = :room " +
            "AND r.status = 'ONAYLANDI' " +
            "AND ((r.reservationDate BETWEEN :startDate AND :endDate) " +
            "OR (r.endDate BETWEEN :startDate AND :endDate) " +
            "OR (:startDate BETWEEN r.reservationDate AND r.endDate))")
    List<Reservation> findByRoomAndDateRange(@Param("room") Room room,
                                             @Param("startDate") LocalDate startDate,
                                             @Param("endDate") LocalDate endDate);

    List<Reservation> findByRoomAndStatus(Room room, String status);
}