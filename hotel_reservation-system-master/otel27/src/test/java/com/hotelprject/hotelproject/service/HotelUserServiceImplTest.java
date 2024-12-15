package com.hotelprject.hotelproject.service;

import com.hotelprject.hotelproject.model.HotelUser;
import com.hotelprject.hotelproject.repository.HotelUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HotelUserServiceImplTest {

    @Mock
    private HotelUserRepository hotelUserRepository;

    @InjectMocks
    private HotelUserServiceImpl hotelUserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Mockito'yu başlatır.
    }

    @Test
    void testExistsByEmail() {
        // Arrange
        String email = "test@example.com";
        when(hotelUserRepository.existsByEmail(email)).thenReturn(true);

        // Act
        boolean exists = hotelUserService.existsByEmail(email);

        // Assert
        assertTrue(exists);
        verify(hotelUserRepository, times(1)).existsByEmail(email); // Metodun çağrıldığını doğrular.
    }

    @Test
    void testSave() {
        // Arrange
        HotelUser hotelUser = new HotelUser();
        hotelUser.setEmail("test@example.com");
        hotelUser.setPassword("password123");

        // Act
        hotelUserService.save(hotelUser);

        // Assert
        verify(hotelUserRepository, times(1)).save(hotelUser); // Kaydetme işleminin çağrıldığını doğrular.
    }

    @Test
    void testFindByEmailAndPassword() {
        // Arrange
        String email = "test@example.com";
        String password = "password123";
        HotelUser hotelUser = new HotelUser();
        hotelUser.setEmail(email);
        hotelUser.setPassword(password);

        when(hotelUserRepository.findByEmailAndPassword(email, password)).thenReturn(Optional.of(hotelUser));

        // Act
        Optional<HotelUser> result = hotelUserService.findByEmailAndPassword(email, password);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());
        assertEquals(password, result.get().getPassword());
        verify(hotelUserRepository, times(1)).findByEmailAndPassword(email, password); // Çağrıyı doğrular.
    }
}
