package com.hotelprject.hotelproject.repository;
import com.hotelprject.hotelproject.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
public interface RoomRepository extends JpaRepository<Room, Long > {
    List<Room> findAllByAvailableIsTrue();
}