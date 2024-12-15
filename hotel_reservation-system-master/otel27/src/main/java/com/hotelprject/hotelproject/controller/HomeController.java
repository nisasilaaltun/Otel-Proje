package com.hotelprject.hotelproject.controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    @GetMapping("/home")
    public String homePage(HttpSession session, Model model) {
        return "home";  // home.html dosyasını döndürür
    }
}