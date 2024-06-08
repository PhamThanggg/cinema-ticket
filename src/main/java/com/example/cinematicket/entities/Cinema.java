package com.example.cinematicket.entities;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "name_cinema")
    private String name;

    private String address;

    private String status;
}
