package com.example.cinematicket.repositories;

import com.example.cinematicket.dtos.responses.CinemaScheduleResponse;
import com.example.cinematicket.entities.Cinema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    boolean existsByName(String name);

    Page<Cinema> findByNameContaining(String name, PageRequest request);

    List<Cinema> findByAreaId(Long areaId);

    @Query("SELECT c, s FROM Cinema c " +
            "LEFT JOIN c.cinemaRooms cr " +
            "LEFT JOIN cr.schedules s " +
            "LEFT JOIN s.movies m " +
            "LEFT JOIN c.area a " +
            "WHERE (:cinemaId IS NULL OR c.id = :cinemaId) " +
            "AND (:movieId IS NULL OR m.id = :movieId) " +
            "AND (:areaId IS NULL OR a.id = :areaId) " +
            "AND (:screeningDate IS NULL OR s.screeningDate = :screeningDate)")
    List<Object[]> findCinemasWithSchedules(
            @Param("cinemaId") Long cinemaId,
            @Param("movieId") Long movieId,
            @Param("areaId") Long areaId,
            @Param("screeningDate") LocalDate screeningDate
    );

    @Query("SELECT c FROM Cinema c " +
            "WHERE (:name IS NULL OR c.name LIKE %:name%) ")
    Page<Cinema> findCinemasOrName(
            @Param("name") String name,
            PageRequest pageRequest
    );
}
