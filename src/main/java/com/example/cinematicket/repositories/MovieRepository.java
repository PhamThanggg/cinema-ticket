package com.example.cinematicket.repositories;

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

    @Query("SELECT m FROM Movie m " +
            "JOIN Schedule s ON s.movies.id = m.id " +
            "JOIN CinemaRoom cr ON cr.id = s.cinemaRooms.id " +
            "JOIN Cinema c ON c.id = cr.cinema.id " +
            "WHERE c.area.id = :areaId " +
            "AND (:status IS NULL OR m.status = :status)")
    Page<Movie> findMoviesByAreaIdAndStatus(@Param("areaId") Long areaId,
                                            @Param("status") Integer status,
                                            Pageable pageable);

    @Query("SELECT m FROM Movie m WHERE (:status IS NULL OR m.status = :status)")
    Page<Movie> findByStatus(@Param("status") int status, Pageable pageable);

    boolean existsByProducerAndDuration(String producer, String duration);
}
