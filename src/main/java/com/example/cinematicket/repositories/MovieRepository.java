package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m WHERE " +
            "(:nameMovie IS NULL OR :nameMovie = '' OR m.nameMovie LIKE %:nameMovie%) AND " +
            "(:status IS NULL OR m.status = :status) AND " +
            "(:genreIds IS NULL OR EXISTS (SELECT g.id FROM m.genres g WHERE g.id IN :genreIds))")
    Page<Movie> findMovieOrGenre(@Param("nameMovie") String nameMovie,
                                 @Param("status") Integer status,
                           @Param("genreIds") Set<Integer> genreIds,
                           Pageable pageable);

    @Query("SELECT m FROM Movie m " +
            "LEFT JOIN m.genres g " +
            "LEFT JOIN Schedule s ON s.movies.id = m.id " +
            "LEFT JOIN CinemaRoom cr ON cr.id = s.cinemaRooms.id " +
            "LEFT JOIN Cinema c ON c.id = cr.cinema.id " +
            "WHERE (:areaId IS NULL OR c.area.id = :areaId) AND " +
            "(:status IS NULL OR m.status = :status) AND " +
            "(:genreId IS NULL OR g.id = :genreId) AND " +
            "(:name IS NULL OR :name = '' OR m.nameMovie LIKE %:name%) " +
            "GROUP BY m")
    Page<Movie> findMoviesByAreaIdAndStatus(@Param("areaId") Long areaId,
                                            @Param("genreId") Long genreId,
                                            @Param("name") String name,
                                            @Param("status") Integer status,
                                            Pageable pageable);

    @Query("SELECT m FROM Movie m WHERE (:status IS NULL OR m.status = :status)")
    Page<Movie> findByStatus(@Param("status") int status, Pageable pageable);

    @Query("SELECT m FROM Movie m WHERE m.status = 1 ")
    List<Movie> findMovieShowNow();

    boolean existsByProducerAndDurationAndNameMovie(String producer, String duration, String nameMovie);

}
