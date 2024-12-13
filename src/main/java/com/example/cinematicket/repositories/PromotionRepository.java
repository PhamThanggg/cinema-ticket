package com.example.cinematicket.repositories;


import com.example.cinematicket.entities.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface PromotionRepository extends JpaRepository<Promotion, String> {
    boolean existsByName(String name);

    @Query("SELECT p FROM Promotion p WHERE " +
            "(p.startDate IS NULL OR p.startDate <= CURRENT_DATE) AND " +
            "(p.endDate IS NULL OR p.endDate >= CURRENT_DATE)")
    List<Promotion> findAllValidPromotions();

    @Query("SELECT p FROM Promotion p WHERE p.name = :name AND " +
            "(p.startDate IS NULL OR p.startDate <= CURRENT_DATE) AND " +
            "(p.endDate IS NULL OR p.endDate >= CURRENT_DATE)")
    Optional<Promotion> findValidByName(@Param("name") String name);

    @Query("SELECT pr FROM Promotion pr " +
            "WHERE (:name IS NULL OR :name = '' OR pr.name LIKE %:name%) " +
            "AND (:promotionType IS NULL OR :promotionType = '' OR pr.promotionType = :promotionType) " +
            "AND (:dateNow IS NULL OR pr.endDate >= :dateNow) " +
            "AND (:dateNow2 IS NULL OR pr.endDate <= :dateNow2)")
    Page<Promotion> findByPromotionOrName(
            @Param("name") String name,
            @Param("promotionType") String promotionType,
            @Param("dateNow") LocalDate dateNow,
            @Param("dateNow2") LocalDate dateNow2,
            Pageable pageable
    );

    @Query("SELECT pr FROM Promotion pr " +
            "WHERE (:name IS NULL OR :name = '' OR pr.name LIKE %:name%) " +
            "AND (pr.promotionType = 'INFO') " +
            "AND (pr.endDate >= :dateNow) ")
    Page<Promotion> findByPromotionInfoOrName(
            @Param("name") String name,
            @Param("dateNow") LocalDate dateNow,
            Pageable pageable
    );
}
