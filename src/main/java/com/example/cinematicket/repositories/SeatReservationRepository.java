package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.SeatReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SeatReservationRepository extends JpaRepository<SeatReservation, Long> {
    @Query("SELECT sr.cinemaSeat.id " +
            "FROM SeatReservation sr " +
            "WHERE sr.schedule.id = :scheduleId " +
            "AND sr.cinemaSeat.id IN :seatIds " +
            "AND sr.expiryTime > :expiryTime")
    Set<Long> findBookedSeatsByScheduleIdAndSeatIds(
            @Param("scheduleId") Long scheduleId,
            @Param("seatIds") Set<Long> seatIds,
            @Param("expiryTime")LocalDateTime expiryTime
            );

    @Modifying
    @Query("DELETE FROM SeatReservation sr WHERE sr.cinemaSeat.id IN :cinemaSeatIds")
    void deleteByCinemaSeatIds(Set<Long> cinemaSeatIds);

    @Modifying
    @Query("DELETE FROM SeatReservation sr " +
            "WHERE sr.cinemaSeat.id = :cinemaSeatId " +
            "AND sr.schedule.id = :scheduleId " +
            "AND sr.expiryTime > :expiryTime")
    void deleteByCinemaSeatId(Long cinemaSeatId, Long scheduleId, LocalDateTime expiryTime);


    @Query("SELECT sr " +
            "FROM SeatReservation sr " +
            "WHERE sr.schedule.id = :scheduleId " +
            "AND sr.cinemaSeat.id = :cinemaSeatId " +
            "AND sr.expiryTime > :expiryTime")
    Optional<SeatReservation> findByCinemaSeatIdAndScheduleId(Long cinemaSeatId, Long scheduleId, LocalDateTime expiryTime);

    @Modifying
    @Transactional
    @Query("UPDATE SeatReservation sr SET sr.status = 1 WHERE sr.cinemaSeat.id IN :seatIds")
    void updateStatusByIds(Set<Long> seatIds);

    @Query("SELECT COUNT(sr) > 0 FROM SeatReservation sr WHERE sr.cinemaSeat.id IN :seatIds AND sr.userId = :userId")
    boolean existsByIdInAndUserId(Set<Long> seatIds, String userId);
}
