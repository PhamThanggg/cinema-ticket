package com.example.cinematicket.services;

import com.example.cinematicket.dtos.requests.GenreRequest;
import com.example.cinematicket.dtos.requests.UserCreationRequest;
import com.example.cinematicket.dtos.requests.UserUpdateRequest;
import com.example.cinematicket.dtos.responses.GenreResponse;
import com.example.cinematicket.dtos.responses.UserResponse;
import org.springframework.data.domain.Page;

public interface IGenreService {
    GenreResponse createGenre(GenreRequest request);

    GenreResponse findById(Long id);

    Page<GenreResponse> getAllGenre(int page, int limit);

    Page<GenreResponse> searchGenre(String search, int page, int limit);

    GenreResponse updateGenre(Long id, GenreRequest request);

    void deleteGenre(Long id);
}
