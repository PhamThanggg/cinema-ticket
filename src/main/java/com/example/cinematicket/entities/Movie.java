package com.example.cinematicket.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table(name="movies")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movie")
    Long id;

    @Column(name = "name_movie")
    String nameMovie;

    @Column(name = "title_movie")
    String titleMovie;

    String duration;

    String language;

    String director;

    String performers;

    @Column(name="age_limit")
    int ageLimit;

    @Column(name = "image_movie")
    String image;

    String trailer;

    String status;

    String description;

}
