package com.example.cinematicket.services.cinemaRoom;

import com.example.cinematicket.dtos.requests.cinemaRoom.CinemaRoomRequest;
import com.example.cinematicket.dtos.requests.cinemaRoom.CinemaRoomUpdateRequest;
import com.example.cinematicket.dtos.responses.CinemaRoomResponse;
import org.springframework.data.domain.Page;

public interface ICinemaRoomService {
    CinemaRoomResponse createCinemaRoom(CinemaRoomRequest request);

    CinemaRoomResponse getCinemaRoomById(Long id);

    Page<CinemaRoomResponse> getAllCinemaRoom(int page, int limit, Long cinema_id);

    Page<CinemaRoomResponse> searchCinemaRoom(String name, Integer status, Long cinema_id, int page, int limit);

    Long totalCinemaRoom(Long cinemaId);

    Long totalCinemaRoomSearch(String name);

    CinemaRoomResponse updateCinemaRoom(Long id, CinemaRoomUpdateRequest request);

    void deleteCinemaRoom(Long id);
}
