package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    boolean existsByName(String name);
    Page<RoomType> findByNameContaining(String name, PageRequest pageRequest);
    Long countByNameContaining(String name);
}
