package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.CinemaRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRoomRepository extends JpaRepository<CinemaRoom, Long> {
    boolean existsByName(String name);
    Page<CinemaRoom> findByNameContainingAndCinemaId(String name, Long id, PageRequest request);

    Page<CinemaRoom> findByCinemaId(Long cinemaId, Pageable request);
    Long countByCinemaId(Long cinema_id);

    Long countByCinemaName(String name);
}
