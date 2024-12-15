package com.hotelprject.hotelproject.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private Boolean available;
    private Integer size;
    private String roomPictureName;
    @OneToMany(fetch = FetchType.LAZY)
    private List<RoomProperty> roomProperties;
}