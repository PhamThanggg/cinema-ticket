package com.example.cinematicket.services.cinema;

import com.example.cinematicket.dtos.requests.CinemaRequest;
import com.example.cinematicket.dtos.responses.CinemaResponse;
import com.example.cinematicket.dtos.responses.CinemaScheduleResponse;
import com.example.cinematicket.dtos.responses.schedule.ScheduleResponse;
import com.example.cinematicket.entities.Area;
import com.example.cinematicket.entities.Cinema;
import com.example.cinematicket.entities.Schedule;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.CinemaMapper;
import com.example.cinematicket.mapper.ScheduleMapper;
import com.example.cinematicket.repositories.AreaRepository;
import com.example.cinematicket.repositories.CinemaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CinemaService implements ICinemaService {
    CinemaRepository cinemaRepository;
    CinemaMapper cinemaMapper;
    AreaRepository areaRepository;
    ScheduleMapper scheduleMapper;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public CinemaResponse createCinema(CinemaRequest request) {
        if(cinemaRepository.existsByName(request.getName()))
            throw new AppException(ErrorCode.CINEMA_EXISTED);

        Area area = areaRepository.findById(request.getIdArea())
                .orElseThrow(() -> new AppException(ErrorCode.AREA_NOT_EXISTS));

        LocalDate localDate = LocalDate.now();
        Cinema cinema = cinemaMapper.toCinema(request);
        cinema.setArea(area);
        cinema.setCreatedDate(localDate);
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
    public List<CinemaResponse> getAllCinema(Long areaId) {
        return cinemaRepository.findByAreaId(areaId).stream().map(cinemaMapper::toCinemaResponse).toList();
    }

    @Override
    public List<CinemaScheduleResponse> getCinemaSchedule(Long cinemaId, Long movieId, Long areaId, LocalDate screeningDate) {
        List<Object[]> results = cinemaRepository.findCinemasWithSchedules(cinemaId, movieId, areaId, screeningDate);

        Map<Long, CinemaScheduleResponse> responseMap = new HashMap<>();

        for (Object[] result : results) {
            Cinema cinema = (Cinema) result[0];
            Schedule schedule = (Schedule) result[1];

            CinemaScheduleResponse response = responseMap.get(cinema.getId());
            if (response == null) {
                response = new CinemaScheduleResponse(cinema.getId(), cinema.getName());
                responseMap.put(cinema.getId(), response);
            }

            if (schedule != null) {
                ScheduleResponse scheduleResponse = scheduleMapper.toScheduleResponse(schedule);

                response.addSchedule(scheduleResponse);
            }
        }

        return new ArrayList<>(responseMap.values());
    }

    @Override
    public Page<CinemaResponse> searchCinema(String name, Integer status, Long areaId, int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        Page<CinemaResponse> pageUser = cinemaRepository.findCinemasOrName(name, status, areaId, pageRequest).map(cinemaMapper::toCinemaResponse);;
        return pageUser;
    }

    @Override
    public Long totalCinema() {
        return cinemaRepository.count();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCinema(Long id) {
        cinemaRepository.deleteById(id);
    }
}
