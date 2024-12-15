package com.hotelprject.hotelproject.controller;
import com.hotelprject.hotelproject.model.HotelUser;
import com.hotelprject.hotelproject.service.HotelUserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final HotelUserService hotelUserService;
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        Optional<HotelUser> hotelUserOptional = hotelUserService.findByEmailAndPassword(email, password);
        if (hotelUserOptional.isPresent()) {
            session.setAttribute("loggedInUser", hotelUserOptional.get().getName());
            return "redirect:/home";
        } else {
            model.addAttribute("loginError", "Email veya şifre yanlış. Lütfen tekrar deneyin!");
            return "login";
        }
    }
}