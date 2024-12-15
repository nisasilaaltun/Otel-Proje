package com.hotelprject.hotelproject.controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class LogoutController {
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // Kullanıcı oturumunu sonlandır
        HttpSession session = request.getSession(false); // Oturum varsa al
        if (session != null) {
            session.invalidate(); // Oturumu sonlandır
        }
        // Giriş ekranına yönlendir
        return "redirect:/home";
    }
}