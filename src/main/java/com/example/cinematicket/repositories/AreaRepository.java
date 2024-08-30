package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {
    boolean existsByAreaName(String name);
}
