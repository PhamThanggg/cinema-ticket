package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.CinemaSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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


//    @Query("SELECT s FROM CinemaSeat s " +
//            "JOIN s.seatType st " +
//            "JOIN s.seatReservations sr " +
//            "WHERE sr.schedule.id = :scheduleId AND sr.status = :status " +
//            "AND sr.userId = :userId AND sr.expiryTime > :timeNow")
//List<CinemaSeat> findBySeatBooked(Long scheduleId, int status, Long userId, LocalDateTime timeNow);

    @Query(nativeQuery = true, value = """
    SELECT s.id AS seat_id, 
           s.name_seat, 
           s.row_seat, 
           s.column_seat, 
           s.status AS seat_status, 
           s.id_cinema_room AS cinema_room_id,
           sr.id AS reservation_id, 
           sr.user_id, 
           sr.schedule_id, 
           sr.status AS reservation_status, 
           sr.reservation_time, 
           sr.expiry_time,
           st.id_seat_type,
           st.name_seat_type,
           st.price
    FROM cinema_seats s
    JOIN seat_type st ON s.id_seat_type = st.id_seat_type
    LEFT JOIN seat_reservation sr ON sr.seat_id = s.id 
    WHERE sr.expiry_time = (
        SELECT MAX(sr2.expiry_time)
        FROM seat_reservation sr2
        WHERE sr2.seat_id = s.id
          AND sr2.schedule_id = :scheduleId
          AND sr2.status = :status
          AND sr2.expiry_time > :timeNow
    )
""")
    List<Object[]> findBySeatBookedNative(Long scheduleId, int status, LocalDateTime timeNow);


    @Query(nativeQuery = true, value = """
    SELECT s.id AS seat_id, 
           s.name_seat, 
           s.row_seat, 
           s.column_seat, 
           s.status AS seat_status, 
           s.id_cinema_room AS cinema_room_id,
           sr.id AS reservation_id, 
           sr.user_id, 
           sr.schedule_id, 
           sr.status AS reservation_status, 
           sr.reservation_time, 
           sr.expiry_time,
           st.id_seat_type,
           st.name_seat_type,
           st.price
    FROM cinema_seats s
    JOIN seat_type st ON s.id_seat_type = st.id_seat_type
    LEFT JOIN seat_reservation sr ON sr.seat_id = s.id 
    WHERE sr.expiry_time = (
        SELECT MAX(sr2.expiry_time)
        FROM seat_reservation sr2
        WHERE sr2.seat_id = s.id
          AND sr2.schedule_id = :scheduleId
          AND sr2.status = :status
    )
""")
    List<Object[]> findBySeatBoughtNative(Long scheduleId, int status);

    @Query(nativeQuery = true, value = """
    SELECT s.id AS seat_id, 
           s.name_seat, 
           s.row_seat, 
           s.column_seat, 
           s.status AS seat_status, 
           s.id_cinema_room AS cinema_room_id,
           sr.id AS reservation_id, 
           sr.user_id, 
           sr.schedule_id, 
           sr.status AS reservation_status, 
           sr.reservation_time, 
           sr.expiry_time,
           st.id_seat_type,
           st.name_seat_type,
           st.price
    FROM cinema_seats s
    JOIN seat_type st ON s.id_seat_type = st.id_seat_type
    LEFT JOIN seat_reservation sr ON sr.seat_id = s.id 
    WHERE sr.expiry_time = (
        SELECT MAX(sr2.expiry_time)
        FROM seat_reservation sr2
         WHERE sr2.seat_id = s.id
    ) AND s.id in (:ids)
""")
    List<Object[]> findBySeatInID(Set<Long> ids);

    @Query("SELECT s FROM CinemaSeat s " +
            "JOIN s.seatType st " +
            "JOIN s.seatReservations sr " +
            "WHERE sr.schedule.id = :scheduleId AND sr.status = :status ")
    List<CinemaSeat> findBySeatBookedBought(Long scheduleId, int status);

    Long countByCinemaRoomId(Long cinemaRoomId);
}
