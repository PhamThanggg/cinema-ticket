package com.example.cinematicket.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Table(name="cinema_room")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemaRoom extends DateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cinema_room")
    Long id;

    @Column(name = "name_cinema_room")
    String name;

    int status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_cinema")
    Cinema cinema;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_room_type")
    RoomType roomType;

    @OneToMany(mappedBy = "cinemaRooms", fetch = FetchType.LAZY)
    private Set<Schedule> schedules;
}
