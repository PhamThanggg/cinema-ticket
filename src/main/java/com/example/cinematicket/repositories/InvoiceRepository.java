package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query("SELECT i FROM Invoice i JOIN FETCH i.invoiceItems WHERE i.id = :invoiceId")
    Optional<Invoice> findByIdWithItems(@Param("invoiceId") Long invoiceId);

    Page<Invoice> findByUserIdAndStatus(Pageable pageable, Long userId, Integer status);

    @Query("SELECT i FROM Invoice i " +
            "JOIN i.schedule s " +
            "JOIN s.movies m " +
            "JOIN s.cinemaRooms cr " +
            "JOIN cr.cinema c " +
            "WHERE (:invoiceId IS NULL OR i.id = :invoiceId) " +
            "AND (:nameMovie IS NULL OR :nameMovie = '' OR m.nameMovie LIKE %:nameMovie%) " +
            "AND (:cinemaId IS NULL OR c.id = :cinemaId) " +
            "AND (:status IS NULL OR s.status = :status) " +
            "AND (:dateRes IS NULL OR FUNCTION('DATE', i.reservationTime) = :dateRes) ")
    Page<Invoice> searchInvoiceAllParams( @Param("invoiceId") Long invoiceId,
                                          @Param("nameMovie") String nameMovie,
                                          @Param("cinemaId") Long cinemaId,
                                          @Param("status") Integer status,
                                          @Param("dateRes") LocalDate dateRes,
                                          Pageable pageable);

    @Query("SELECT SUM(i.amountPaid) " +
            "FROM Invoice i " +
            "JOIN user u " +
            "WHERE u.id = :userId " +
            "AND FUNCTION('YEAR', i.paymentTime) = :currentYear")
    Integer countTotalPriceInvoice(@Param("userId") Long userId,  @Param("currentYear") int currentYear);
}
