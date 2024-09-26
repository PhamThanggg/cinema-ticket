package com.example.cinematicket.repositories;

import com.example.cinematicket.dtos.responses.CinemaSeatResponse;
import com.example.cinematicket.entities.CinemaSeat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CinemaSeatRepository extends JpaRepository<CinemaSeat, Long> {
    boolean existsByCinemaRoomIdAndName(Long cinemaRoomId, String name);

    boolean existsByCinemaRoomIdAndRowAndColum(Long cinemaRoomId, String row, String colum);

    Page<CinemaSeat> findByCinemaRoomId(Long cinemaRoomId, Pageable request);
}
