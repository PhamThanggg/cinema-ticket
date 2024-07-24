package com.example.cinematicket.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

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

    @OneToMany
    @JsonManagedReference
    @Column(name = "image_movie")
    @JoinColumn(name="id_movie")
    List<MovieImage> images;

    String trailer;

    String status;

    String description;

    @Column(name = "star_rating")
    String starRating;

    @ManyToMany
    @JsonManagedReference
    @JoinTable(name = "movie_genres",
            joinColumns = @JoinColumn(name = "id_movie"),
            inverseJoinColumns = @JoinColumn(name = "id_genre"))
    List<Genre> genres;

}
