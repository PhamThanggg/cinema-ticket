package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.Area;
import com.example.cinematicket.entities.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {
    boolean existsByAreaName(String name);

    @Query("SELECT a FROM Area a WHERE " +
            "(:name IS NULL OR :name = '' OR a.areaName LIKE %:name%)")
    Page<Area> findAreaOrName(@Param("name") String name,
                                Pageable pageable);
}
