package com.example.cinematicket.services.cinema;

import com.example.cinematicket.dtos.requests.CinemaRequest;
import com.example.cinematicket.dtos.responses.CinemaResponse;
import com.example.cinematicket.entities.Area;
import com.example.cinematicket.entities.Cinema;

import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.CinemaMapper;
import com.example.cinematicket.repositories.AreaRepository;
import com.example.cinematicket.repositories.CinemaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CinemaService implements ICinemaService {
    CinemaRepository cinemaRepository;
    CinemaMapper cinemaMapper;
    AreaRepository areaRepository;

    @Override
    @PostAuthorize("hasRole('ADMIN')")
    public CinemaResponse createCinema(CinemaRequest request) {
        if(cinemaRepository.existsByName(request.getName()))
            throw new AppException(ErrorCode.CINEMA_EXISTED);

        Area area = areaRepository.findById(request.getIdArea())
                .orElseThrow(() -> new AppException(ErrorCode.AREA_NOT_EXISTS));

        Cinema cinema = cinemaMapper.toCinema(request);
        cinema.setArea(area);
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
    @PostAuthorize("hasRole('ADMIN')")
    public Long totalCinema() {
        return cinemaRepository.count();
    }

    @Override
    @PostAuthorize("hasRole('ADMIN')")
    public CinemaResponse updateCinema(Long id, CinemaRequest request) {
        Cinema cinema = cinemaRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.CINEMA_NOT_EXISTED));
        Area area = areaRepository.findById(request.getIdArea())
                .orElseThrow(() -> new AppException(ErrorCode.AREA_NOT_EXISTS));

        cinemaMapper.updateCinema(cinema, request);
        cinema.setArea(area);
        return cinemaMapper.toCinemaResponse(cinemaRepository.save(cinema));
    }

    @Override
    @PostAuthorize("hasRole('ADMIN')")
    public void deleteCinema(Long id) {
        cinemaRepository.deleteById(id);
    }
}
