package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query("SELECT i FROM Invoice i JOIN FETCH i.invoiceItems WHERE i.id = :invoiceId")
    Optional<Invoice> findByIdWithItems(@Param("invoiceId") Long invoiceId);

    Page<Invoice> findByUserId(Pageable pageable, Long userId);
}
