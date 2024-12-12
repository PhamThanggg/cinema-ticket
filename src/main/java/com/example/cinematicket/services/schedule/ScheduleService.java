package com.example.cinematicket.services.schedule;

import com.example.cinematicket.dtos.requests.ScheduleRequest;
import com.example.cinematicket.dtos.responses.schedule.ScheduleResponse;
import com.example.cinematicket.entities.CinemaRoom;
import com.example.cinematicket.entities.Movie;
import com.example.cinematicket.entities.Schedule;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.ScheduleMapper;
import com.example.cinematicket.repositories.CinemaRoomRepository;
import com.example.cinematicket.repositories.MovieRepository;
import com.example.cinematicket.repositories.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Service
@RequiredArgsConstructor
public class ScheduleService implements IScheduleService {
    private final MovieRepository movieRepository;
    private final CinemaRoomRepository cinemaRoomRepository;
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;


    private static final int TIME_BEFORE_START = 60;

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_SHOWTIME')")
    public ScheduleResponse createSchedule(ScheduleRequest request) {
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_EXISTED));
        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(request.getCinemaRoomId())
                .orElseThrow(() -> new AppException(ErrorCode.CINEMA_ROOM_NOT_EXISTED));

        int timeMovie = Integer.parseInt(movie.getDuration());
        checkSchedule(request, timeMovie);
        checkOverlappingScreenings(request);

        Schedule schedule = scheduleMapper.toSchedule(request);
        schedule.setCinemaRooms(cinemaRoom);
        schedule.setMovies(movie);
        return scheduleMapper.toScheduleResponse(scheduleRepository.save(schedule));
    }

    @Override
    public ScheduleResponse findById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SCHEDULE_NOT_EXISTS));

        return scheduleMapper.toScheduleResponse(schedule);
    }
    @Override
    public Page<ScheduleResponse> searchSchedule(Long roomId, LocalDate screeningDate, int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "screeningDate"));

        return scheduleRepository.findSchedulesByRoom(roomId, screeningDate, pageable).map(scheduleMapper::toScheduleResponse);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_SHOWTIME')")
    public ScheduleResponse updateSchedule(Long id, ScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SCHEDULE_NOT_EXISTS));

        LocalDateTime currentDateTime = LocalDateTime.now();
        if(ChronoUnit.MINUTES.between(currentDateTime, request.getStartTime()) < 30){
            throw new AppException(ErrorCode.SCHEDULE_EDIT);
        }

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_EXISTED));

        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(request.getCinemaRoomId())
                .orElseThrow(() -> new AppException(ErrorCode.CINEMA_ROOM_NOT_EXISTED));

        if(!schedule.getStartTime().equals(request.getStartTime())
                || !schedule.getEndTime().equals(request.getEndTime())
                || !schedule.getScreeningDate().equals(request.getScreeningDate())
        ){

            int timeMovie = Integer.parseInt(movie.getDuration());
            checkSchedule(request, timeMovie);
            checkOverlappingScreenings(request);
        }

        scheduleMapper.updateSchedule(schedule, request);
        schedule.setCinemaRooms(cinemaRoom);
        schedule.setMovies(movie);
        return scheduleMapper.toScheduleResponse(scheduleRepository.save(schedule));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_SHOWTIME')")
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    private void checkSchedule(ScheduleRequest request, int timeMovie){
        LocalDate localDateStart = request.getStartTime().toLocalDate();
        if(!request.getScreeningDate().equals(localDateStart))
            throw new AppException(ErrorCode.SCHEDULE_DATE_SAME);

        LocalDate curentLocalDate = LocalDate.now();
        if(curentLocalDate.isAfter(request.getScreeningDate())
                || curentLocalDate.isAfter(localDateStart)
        ){
            throw new AppException(ErrorCode.SCHEDULE_DATE);
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        if(ChronoUnit.MINUTES.between(currentDateTime, request.getStartTime()) < TIME_BEFORE_START){
            throw new AppException(ErrorCode.SCHEDULE_TIME);
        }

        LocalDateTime timeEndMovie = request.getEndTime().plusMinutes(timeMovie + 10);
        if(request.getStartTime().isAfter(request.getEndTime())
                && timeEndMovie.isAfter(request.getEndTime()))
            throw new AppException(ErrorCode.SCHEDULE_TIME_MOVIE);
    }

    private void checkOverlappingScreenings(ScheduleRequest request){
        if(scheduleRepository.findOverlappingScreenings
                (request.getStartTime(), request.getEndTime(), request.getCinemaRoomId()) > 0
        ){
            throw new AppException(ErrorCode.SCHEDULE_TIME_EXISTS);
        }
    }
}
