package com.example.cinematicket.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Table(name = "cinemas")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cinema")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private Area area;

    @Column(name = "name_cinema")
    private String name;

    private String address;

    private int status;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @OneToMany(mappedBy = "cinema", fetch = FetchType.LAZY)
    private Set<CinemaRoom> cinemaRooms;
}
