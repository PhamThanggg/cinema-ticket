package com.example.cinematicket.services.cinema;

import com.example.cinematicket.dtos.requests.CinemaRequest;
import com.example.cinematicket.dtos.responses.CinemaResponse;
import com.example.cinematicket.dtos.responses.CinemaScheduleResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface ICinemaService {
    CinemaResponse createCinema(CinemaRequest request);

    CinemaResponse getCinemaById(Long id);

    List<CinemaResponse> getAllCinema();

    List<CinemaResponse> getAllCinema(Long areaId);

    List<CinemaScheduleResponse> getCinemaSchedule(Long cinemaId, Long movieId, Long areaId, LocalDate screeningDate);

    Page<CinemaResponse> searchCinema(String name, Integer status, Long areaId, int page, int limit);

    Long totalCinema();

    CinemaResponse updateCinema(Long id, CinemaRequest request);

    void deleteCinema(Long id);
}
