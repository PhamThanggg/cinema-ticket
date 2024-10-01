package com.example.cinematicket.services.moviePeople;

import com.example.cinematicket.dtos.requests.MoviePeopleRequest;
import com.example.cinematicket.dtos.responses.MoviePeopleResponse;
import com.example.cinematicket.entities.MoviePeople;
import com.example.cinematicket.entities.MovieRoleType;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.MoviePeopleMapper;
import com.example.cinematicket.mapper.MovieRoleTypeRepository;
import com.example.cinematicket.repositories.MoviePeopleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MoviePeopleService implements IMoviePeopleService {
    MoviePeopleRepository moviePeopleRepository;
    MovieRoleTypeRepository movieRoleTypeRepository;
    MoviePeopleMapper moviePeopleMapper;

    @Override
    @PostAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_MOVIE')")
    public MoviePeopleResponse createMoviePeople(MoviePeopleRequest request) {
        MovieRoleType roleType = movieRoleTypeRepository.findById(request.getIdRoleType())
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_TYPE_NOT_EXISTS));

        if(moviePeopleRepository.existsByIdAndNameAndDateOfBirth(
                request.getIdRoleType(), request.getName(), request.getDateOfBirth())
        ){
            throw new AppException(ErrorCode.MOVIE_PP_NOT_EXISTS);
        }

        MoviePeople moviePeople = moviePeopleMapper.toMoviePeople(request);
        moviePeople.setRoleType(roleType);
        return  moviePeopleMapper.toMoviePeopleResponse(moviePeopleRepository.save(moviePeople));
    }

    @Override
    public MoviePeopleResponse findById(Long id) {
        MoviePeople moviePeople = moviePeopleRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_PP_NOT_EXISTS));

        return moviePeopleMapper.toMoviePeopleResponse(moviePeopleRepository.save(moviePeople));
    }

    @Override
    public Page<MoviePeopleResponse> getMoviePeopleALl(String name, int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);

        return moviePeopleRepository.findName(name, pageable).map(moviePeopleMapper::toMoviePeopleResponse);
    }

    @Override
    @PostAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_MOVIE')")
    public MoviePeopleResponse updateMoviePeople(MoviePeopleRequest request, Long id) {
        MoviePeople moviePeople = moviePeopleRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_PP_NOT_EXISTS));

        MovieRoleType roleType = movieRoleTypeRepository.findById(request.getIdRoleType())
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_TYPE_NOT_EXISTS));

        if(!request.getIdRoleType().equals(moviePeople.getId())
            || !request.getName().equals(moviePeople.getName())
            || !request.getDateOfBirth().equals(moviePeople.getDateOfBirth())
        ){
            if(moviePeopleRepository.existsByIdAndNameAndDateOfBirth(
                    request.getIdRoleType(), request.getName(), request.getDateOfBirth())
            ){
                throw new AppException(ErrorCode.MOVIE_PP_EXISTS);
            }
        }

        moviePeopleMapper.updateMoviePeople(moviePeople, request);
        moviePeople.setRoleType(roleType);
        return moviePeopleMapper.toMoviePeopleResponse(moviePeopleRepository.save(moviePeople));
    }

    @Override
    @PostAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_MOVIE')")
    public void deleteMoviePeople(Long id) {
        moviePeopleRepository.deleteById(id);
    }
}
