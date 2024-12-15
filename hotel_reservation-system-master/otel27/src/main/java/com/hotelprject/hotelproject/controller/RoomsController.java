package com.hotelprject.hotelproject.controller;
import com.hotelprject.hotelproject.model.Room;
import com.hotelprject.hotelproject.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
@Controller
@RequiredArgsConstructor
public class RoomsController {
    private final RoomService roomService;
    @GetMapping("/rooms")
    public String showRooms(Model model) {
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);
        return "rooms"; // rooms.html sayfasını render eder
    }
}