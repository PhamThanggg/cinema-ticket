package com.example.cinematicket.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "seat_type")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seat_type")
    Long id;

    @Column(name = "name_seat_type")
    String name;

    double price;
}
