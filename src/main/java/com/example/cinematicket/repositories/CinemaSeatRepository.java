package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.CinemaSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaSeatRepository extends JpaRepository<CinemaSeat, Long> {
    boolean existsByCinemaRoomIdAndName(Long cinemaRoomId, String name);

    boolean existsByCinemaRoomIdAndRowAndColum(Long cinemaRoomId, String row, String colum);

    List<CinemaSeat> findByCinemaRoomId(Long cinemaRoomId);

    @Query("SELECT s FROM CinemaSeat s " +
            "LEFT JOIN s.seatType st " +
            "LEFT JOIN s.cinemaRoom cr " +
            "LEFT JOIN s.seatReservations " +
            "LEFT JOIN cr.schedules d " +
            "WHERE d.id = :scheduleId")
    List<CinemaSeat> findBySeatCinemaRoomId(@Param("scheduleId") Long scheduleId);


    @Query("SELECT s FROM CinemaSeat s " +
            "JOIN s.seatType st " +
            "JOIN s.seatReservations sr " +
            "WHERE sr.schedule.id = :scheduleId AND sr.status = :status AND sr.userId = :userId") // thêm check tgian
    List<CinemaSeat> findBySeatBooked(Long scheduleId, int status, Long userId);

    Long countByCinemaRoomId(Long cinemaRoomId);
}
