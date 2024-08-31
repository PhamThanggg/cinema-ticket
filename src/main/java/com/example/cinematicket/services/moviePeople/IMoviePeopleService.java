package com.example.cinematicket.services.moviePeople;

import com.example.cinematicket.dtos.requests.AreaRequest;
import com.example.cinematicket.dtos.requests.MoviePeopleRequest;
import com.example.cinematicket.dtos.responses.AreaResponse;
import com.example.cinematicket.dtos.responses.MoviePeopleResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IMoviePeopleService {
    MoviePeopleResponse createMoviePeople(MoviePeopleRequest request);

    MoviePeopleResponse findById(Long id);

    Page<MoviePeopleResponse> getMoviePeopleALl(String name, int page, int limit);

    MoviePeopleResponse updateMoviePeople(MoviePeopleRequest request, Long id);

    void deleteMoviePeople(Long id);
}
