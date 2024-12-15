package com.hotelprject.hotelproject.controller;

import com.hotelprject.hotelproject.model.HotelUser;
import com.hotelprject.hotelproject.model.dto.UserRegistrationDto;
import com.hotelprject.hotelproject.service.HotelUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RegisterController.class)
class RegisterControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelUserService hotelUserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShowRegistrationForm_ShouldReturnRegistrationPage() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    void testRegisterUser_SuccessfulRegistration() throws Exception {
        // Arrange
        String email = "user@example.com";
        String password = "password";
        String name = "John Doe";

        UserRegistrationDto userDto = new UserRegistrationDto();
        userDto.setName(name);
        userDto.setEmail(email);
        userDto.setPassword(password);

        when(hotelUserService.existsByEmail(email)).thenReturn(false);

        // Act & Assert
        mockMvc.perform(post("/register")
                        .param("name", name)
                        .param("email", email)
                        .param("password", password))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?success"));

        verify(hotelUserService, times(1)).existsByEmail(email);
        verify(hotelUserService, times(1)).save(any(HotelUser.class));
    }

    @Test
    void testRegisterUser_EmailAlreadyExists() throws Exception {
        // Arrange
        String email = "user@example.com";
        String password = "password";
        String name = "John Doe";

        UserRegistrationDto userDto = new UserRegistrationDto();
        userDto.setName(name);
        userDto.setEmail(email);
        userDto.setPassword(password);

        when(hotelUserService.existsByEmail(email)).thenReturn(true);

        // Act & Assert
        mockMvc.perform(post("/register")
                        .param("name", name)
                        .param("email", email)
                        .param("password", password))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("emailError"));

        verify(hotelUserService, times(1)).existsByEmail(email);
        verify(hotelUserService, times(0)).save(any(HotelUser.class));
    }

}
