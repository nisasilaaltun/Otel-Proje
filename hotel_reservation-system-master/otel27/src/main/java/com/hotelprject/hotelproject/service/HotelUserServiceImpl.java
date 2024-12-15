package com.hotelprject.hotelproject.service;

import com.hotelprject.hotelproject.model.HotelUser;
import com.hotelprject.hotelproject.model.enums.UserRole;
import com.hotelprject.hotelproject.repository.HotelUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelUserServiceImpl implements HotelUserService{

    private final HotelUserRepository hotelUserRepository;

    public HotelUserServiceImpl(HotelUserRepository hotelUserRepository) {
        this.hotelUserRepository = hotelUserRepository;
    }

    public boolean existsByEmail(String email) {
        return hotelUserRepository.existsByEmail(email);
    }

    public void save(HotelUser hotelUser) {
        hotelUserRepository.save(hotelUser);
    }

    public Optional<HotelUser> findByEmailAndPassword(String email, String password) {
        return hotelUserRepository.findByEmailAndPassword(email, password);
    }

    public Optional<HotelUser> findAdminUserByEmailAndPassword(String email, String password) {
        return hotelUserRepository.findByEmailAndPasswordAndUserRole(email, password, UserRole.ADMIN);
    }

    @Override
    public List<HotelUser> findAll() {
        return hotelUserRepository.findAll();
    }

    @Override
    public HotelUser findById(Long id) {
        return hotelUserRepository.findById(id).orElse(null);
    }

}