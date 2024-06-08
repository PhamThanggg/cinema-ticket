package com.example.cinematicket.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "cinema_room_types")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String status;
}
