package com.example.cinematicket.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table(name="cinema_seats")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemaSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="name_seat")
    String name;

    @OneToOne
    @JoinColumn(name = "id_seat_type")
    SeatType seatType;

    @ManyToOne
    @JoinColumn(name="id_cinema_room")
    CinemaRoom cinemaRoom;

    @Column(name = "row_seat")
    String row;

    @Column(name = "column_seat")
    String colum;
    String status;
}
