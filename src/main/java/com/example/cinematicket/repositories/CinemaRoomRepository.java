package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.CinemaRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRoomRepository extends JpaRepository<CinemaRoom, Long> {
    boolean existsByName(String name);
    Page<CinemaRoom> findByNameContaining(String name, PageRequest request);

    Long countByCinemaId(Long cinema_id);
}
