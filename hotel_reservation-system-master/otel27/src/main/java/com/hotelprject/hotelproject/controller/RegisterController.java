package com.hotelprject.hotelproject.controller;
import com.hotelprject.hotelproject.model.HotelUser;
import com.hotelprject.hotelproject.model.dto.UserRegistrationDto;
import com.hotelprject.hotelproject.model.enums.UserRole;
import com.hotelprject.hotelproject.service.HotelUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
@RequiredArgsConstructor
public class RegisterController {
    private final HotelUserService userService;
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "register";
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserRegistrationDto userDto,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        boolean isUserExists = userService.existsByEmail(userDto.getEmail());
        if (isUserExists) {
            model.addAttribute("emailError", "Bu e-posta adresi zaten kayıtlı.");
            return "register";
        }
        HotelUser hotelUser = HotelUser.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .userRole(UserRole.CLIENT)
                .build();
        userService.save(hotelUser);
        return "redirect:/login?success";
    }
}