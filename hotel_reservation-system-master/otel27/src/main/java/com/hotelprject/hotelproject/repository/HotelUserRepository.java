package com.hotelprject.hotelproject.repository;
import com.hotelprject.hotelproject.model.HotelUser;
import com.hotelprject.hotelproject.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface HotelUserRepository extends JpaRepository<HotelUser, Long> {
    boolean existsByEmail(String email);
    Optional<HotelUser> findByEmailAndPassword(String email, String password);
    Optional<HotelUser> findByEmailAndPasswordAndUserRole(String email, String password, UserRole userRole);
}