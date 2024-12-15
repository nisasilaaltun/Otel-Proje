package com.hotelprject.hotelproject.controller;

import com.hotelprject.hotelproject.model.HotelUser;
import com.hotelprject.hotelproject.model.enums.UserRole;
import com.hotelprject.hotelproject.service.HotelUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UsersController {

    private final HotelUserService hotelUserService;

    @GetMapping("users/edit/{userId}")
    public String editUser(@PathVariable Long userId, Model model) {
        model.addAttribute("user", hotelUserService.findById(userId));
        model.addAttribute("roles", UserRole.values());
        return "edit-user";
    }

    @PostMapping("users/edit")
    public String editUser(@ModelAttribute("user") HotelUser updatedUser) {

        HotelUser user = hotelUserService.findById(updatedUser.getId());

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setUserRole(updatedUser.getUserRole());

        hotelUserService.save(user);

        return "redirect:/admin/manage-users";
    }
}
