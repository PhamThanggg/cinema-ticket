package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.CinemaRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRoomRepository extends JpaRepository<CinemaRoom, Long> {
    boolean existsByNameAndCinemaId(String name, Long cinemaId);

    @Query("SELECT cr FROM CinemaRoom cr " +
            "JOIN cr.cinema c " +
            "WHERE (:name IS NULL OR :name = '' OR cr.name LIKE %:name%) AND " +
            "(:status IS NULL OR cr.status = :status) AND " +
            "c.id = :cinemaId")
    Page<CinemaRoom> cinemaRoomSearch(@Param("name") String name,
                                      @Param("status") Integer status,
                                      @Param("cinemaId") Long cinemaId, PageRequest request);

    Page<CinemaRoom> findByCinemaId(Long cinemaId, Pageable request);

    List<CinemaRoom> findByCinemaId(Long cinemaId);
    Long countByCinemaId(Long cinema_id);

    Long countByCinemaName(String name);
}
