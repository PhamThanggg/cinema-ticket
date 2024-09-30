package com.example.cinematicket.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
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
public class Movie extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movie")
    Long id;

    @Column(name = "name_movie")
    String nameMovie;

    String producer;

    @Column(name = "title_movie")
    String titleMovie;

    String duration;

    String language;

    @Column(name="age_limit")
    int ageLimit;

    @OneToMany(fetch = FetchType.LAZY)
    @Column(name = "image_movie")
    @JoinColumn(name="id_movie")
    Set<MovieImage> images;

    String trailer;

    int status;

    String description;

    @Column(name = "star_rating")
    String starRating;

    @Column(name = "premiere_date")
    LocalDateTime premiereDate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "movie_genres",
            joinColumns = @JoinColumn(name = "id_movie", referencedColumnName = "id_movie"),
            inverseJoinColumns = @JoinColumn(name = "id_genre", referencedColumnName = "id_genre"))
    Set<Genre> genres;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "movie_people_detail",
            joinColumns = @JoinColumn(name = "id_movie", referencedColumnName = "id_movie"),
            inverseJoinColumns = @JoinColumn(name = "id_movie_people", referencedColumnName = "id_movie_people"))
    Set<MoviePeople> moviePeople;

}
