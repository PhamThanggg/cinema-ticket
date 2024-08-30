package com.example.cinematicket.services.cinema;

import com.example.cinematicket.dtos.requests.CinemaRequest;
import com.example.cinematicket.dtos.responses.CinemaResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICinemaService {
    CinemaResponse createCinema(CinemaRequest request);

    CinemaResponse getCinemaById(Long id);

    List<CinemaResponse> getAllCinema();

    Page<CinemaResponse> searchCinema(String name, int page, int limit);

    Long totalCinema();

    CinemaResponse updateCinema(Long id, CinemaRequest request);

    void deleteCinema(Long id);
}
