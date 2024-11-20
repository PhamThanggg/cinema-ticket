package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
//    boolean existsByCinemaSeatIdInAndInvoiceScheduleId(Set<Long> cinemaSeatIds, Long scheduleId);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END " +
            "FROM Ticket t JOIN t.cinemaSeat cs JOIN t.invoice i " +
            "WHERE (cs.id IN :cinemaSeatIds AND i.schedule.id = :scheduleId AND i.status = 1) " +
            "OR (cs.id IN :cinemaSeatIds AND i.schedule.id = :scheduleId AND i.paymentExpirationTime > :timeNow)")
    boolean existsByCinemaSeatIdInAndInvoiceScheduleId(@Param("cinemaSeatIds") Set<Long> cinemaSeatIds,
                                                       @Param("scheduleId") Long scheduleId,
                                                       @Param("timeNow") LocalDateTime timeNow);
}