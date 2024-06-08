package com.example.cinematicket.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table(name="cinema_room")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemaRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cinema_room")
    Long id;

    @Column(name = "name_cinema_room")
    String name;

    String status;

    @ManyToOne
    @JoinColumn(name="id_cinema")
    Cinema cinema;

    @OneToOne
    @JoinColumn(name="id_room_type")
    RoomType roomType;
}
