package com.hotelprject.hotelproject.repository;
import com.hotelprject.hotelproject.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface AdminRepository extends JpaRepository<Admin, Long> {
    // Admini email ve şifre ile bulma
    Optional<Admin> findByEmailAndPassword(String email, String password);
    Optional<Admin> findByEmail(String email);

}