package com.example.cinematicket.services;

import com.example.cinematicket.dtos.requests.CinemaRequest;
import com.example.cinematicket.dtos.responses.CinemaResponse;
import com.example.cinematicket.dtos.responses.UserResponse;
import com.example.cinematicket.entities.Cinema;

import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.CinemaMapper;
import com.example.cinematicket.repositories.CinemaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CinemaService implements ICinemaService{
    CinemaRepository cinemaRepository;
    CinemaMapper cinemaMapper;

    @Override
    public CinemaResponse createCinema(CinemaRequest request) {
        if(cinemaRepository.existsByName(request.getName()))
            throw new AppException(ErrorCode.CINEMA_EXISTED);

        Cinema cinema = cinemaMapper.toCinema(request);
        return cinemaMapper.toCinemaResponse(cinemaRepository.save(cinema));
    }

    @Override
    public CinemaResponse getCinemaById(Long id) {
        Cinema cinema = cinemaRepository.findById(id).
                orElseThrow(()-> new AppException(ErrorCode.CINEMA_NOT_EXISTED));

        return cinemaMapper.toCinemaResponse(cinema);
    }

    @Override
    public List<CinemaResponse> getAllCinema() {
        return cinemaRepository.findAll().stream().map(cinemaMapper::toCinemaResponse).toList();
    }

    @Override
    public Page<CinemaResponse> searchCinema(String name, int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC, "id"));
        Page<CinemaResponse> pageUser;
        if(name == null){
            pageUser = cinemaRepository.findAll(pageRequest).map(cinemaMapper::toCinemaResponse);
        }else{
            pageUser = cinemaRepository.findByNameContaining(name, pageRequest).map(cinemaMapper::toCinemaResponse);
        }
        return pageUser;
    }

    @Override
    public Long totalCinema() {
        return cinemaRepository.count();
    }

    @Override
    public CinemaResponse updateCinema(Long id, CinemaRequest request) {
        Cinema cinema = cinemaRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.CINEMA_NOT_EXISTED));

        cinemaMapper.updateCinema(cinema, request);
        return cinemaMapper.toCinemaResponse(cinemaRepository.save(cinema));
    }

    @Override
    public void deleteCinema(Long id) {
        cinemaRepository.deleteById(id);
    }
}
