package com.hotelprject.hotelproject.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userInfo;
    @ManyToOne
    private Room room;
    private LocalDate reservationDate;
    private LocalDate endDate;
    private String status;
}