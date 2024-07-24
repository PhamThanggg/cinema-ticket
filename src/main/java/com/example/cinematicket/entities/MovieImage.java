package com.example.cinematicket.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table(name="movie_images")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieImage {
    public static final int MAXIMUM_IMAGES_PER_MOVIE = 5;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_movie_images")
    Long id;

    @Column(name="image_url")
    String imageUrl;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_movie")
    private Movie movie;


}
