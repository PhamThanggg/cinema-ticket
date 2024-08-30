package com.example.cinematicket.repositories;

import com.example.cinematicket.dtos.responses.MovieResponse;
import com.example.cinematicket.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m WHERE " +
            "(:nameMovie IS NULL OR :nameMovie = '' OR m.nameMovie LIKE %:nameMovie%) AND " +
            "(:genreIds IS NULL OR EXISTS (SELECT g.id FROM m.genres g WHERE g.id IN :genreIds))")
    Page<Movie> findMovieOrGenre(@Param("nameMovie") String nameMovie,
                           @Param("genreIds") Set<Integer> genreIds,
                           Pageable pageable);

}
