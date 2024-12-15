package com.hotelprject.hotelproject.controller;

import com.hotelprject.hotelproject.model.HotelUser;
import com.hotelprject.hotelproject.service.HotelUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginController.class)
class LoginControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelUserService hotelUserService;

    private MockHttpSession session;

    @BeforeEach
    void setUp() {
        session = new MockHttpSession();
    }

    @Test
    void testLoginPage_ShouldReturnLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void testLogin_SuccessfulLogin() throws Exception {
        // Arrange
        String email = "user@example.com";
        String password = "password";
        HotelUser hotelUser = new HotelUser();
        hotelUser.setName("John Doe");
        hotelUser.setEmail(email);
        hotelUser.setPassword(password);

        when(hotelUserService.findByEmailAndPassword(email, password))
                .thenReturn(Optional.of(hotelUser));

        // Act & Assert
        mockMvc.perform(post("/login")
                        .param("email", email)
                        .param("password", password)
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));

        verify(hotelUserService, times(1)).findByEmailAndPassword(email, password);
    }

    @Test
    void testLogin_FailedLogin() throws Exception {
        // Arrange
        String email = "user@example.com";
        String password = "incorrectPassword";

        when(hotelUserService.findByEmailAndPassword(email, password))
                .thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(post("/login")
                        .param("email", email)
                        .param("password", password)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attribute("loginError", "Email veya şifre yanlış. Lütfen tekrar deneyin!"));

        verify(hotelUserService, times(1)).findByEmailAndPassword(email, password);
    }
}
