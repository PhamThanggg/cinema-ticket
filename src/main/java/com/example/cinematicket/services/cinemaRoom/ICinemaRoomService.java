package com.example.cinematicket.services.cinemaRoom;

import com.example.cinematicket.dtos.requests.CinemaRoomRequest;
import com.example.cinematicket.dtos.responses.CinemaRoomResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ICinemaRoomService {
    CinemaRoomResponse createCinemaRoom(CinemaRoomRequest request);

    CinemaRoomResponse getCinemaRoomById(Long id);

    Page<CinemaRoomResponse> getAllCinemaRoom(int page, int limit, Long cinema_id);

    Page<CinemaRoomResponse> searchCinemaRoom(String name, Long cinema_id, int page, int limit);

    Long totalCinemaRoom(Long cinemaId);

    Long totalCinemaRoomSearch(String name);

    CinemaRoomResponse updateCinemaRoom(Long id, CinemaRoomRequest request);

    void deleteCinemaRoom(Long id);
}
