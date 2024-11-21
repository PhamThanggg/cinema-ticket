package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.Movie;
import com.example.cinematicket.entities.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT COUNT(s) FROM Schedule s " +
            "WHERE (s.startTime < :endTime " +
            "AND s.endTime > :startTime " +
            "AND s.cinemaRooms.id = :roomId)")
    int findOverlappingScreenings(@Param("startTime") LocalDateTime startTime,
                                  @Param("endTime") LocalDateTime endTime,
                                  @Param("roomId") Long roomId);

    @Query("SELECT s FROM Schedule s " +
            "WHERE (s.startTime < :endTime " +
            "AND s.endTime > :startTime " +
            "AND s.cinemaRooms.id = :roomId)")
    Schedule findOverlappingScreeningss(@Param("startTime") LocalDateTime startTime,
                                  @Param("endTime") LocalDateTime endTime,
                                  @Param("roomId") Long roomId);

    @Query("SELECT s FROM Schedule s " +
            "JOIN s.cinemaRooms cr " +
            "WHERE cr.id = :roomId " +
            "AND s.screeningDate = :screeningDate")
    Page<Schedule> findSchedulesByRoom(
            @Param("roomId") Long roomId,
            @Param("screeningDate") LocalDate screeningDate,
            Pageable Pageable
    );

}

