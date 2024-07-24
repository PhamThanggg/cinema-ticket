package com.example.cinematicket.repositories;

import com.example.cinematicket.dtos.responses.MovieResponse;
import com.example.cinematicket.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
//    @Query("SELECT m FROM movies m WHERE " +
//            "(:nameMovie IS NULL OR m.name_movie = :nameMovie) AND " +
//            "(:genre IS NULL OR m.genre = :genre)")
//    Page<MovieResponse> searchMovies(@Param("nameMovie") String nameMovie, @Param("genre") String genre);

}
