package com.example.cinematicket.services.schedule;

import com.example.cinematicket.dtos.requests.ScheduleRequest;
import com.example.cinematicket.dtos.responses.ScheduleResponse;
import com.example.cinematicket.entities.CinemaRoom;
import com.example.cinematicket.entities.Movie;
import com.example.cinematicket.entities.Schedule;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.ScheduleMapper;
import com.example.cinematicket.repositories.CinemaRoomRepository;
import com.example.cinematicket.repositories.MovieRepository;
import com.example.cinematicket.repositories.ScheduleRepository;
import com.example.cinematicket.services.schedule.IScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public Page<ScheduleResponse> getAllSchedule(int page, int limit) {
        return null;
    }

    @Override
    public Page<ScheduleResponse> searchSchedule(String search, int page, int limit) {
        return null;
    }

    @Override
    public ScheduleResponse updateSchedule(Long id, ScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SCHEDULE_NOT_EXISTS));

        LocalDateTime currentDateTime = LocalDateTime.now();
        if(ChronoUnit.MINUTES.between(currentDateTime, request.getStartTime()) >= 30){
            throw new RuntimeException("You can only edit the showtime 30 minutes before the movie starts");
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

        schedule.setCinemaRooms(cinemaRoom);
        schedule.setMovies(movie);
        return scheduleMapper.toScheduleResponse(scheduleRepository.save(schedule));
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    private void checkSchedule(ScheduleRequest request, int timeMovie){
        LocalDate localDateStart = request.getStartTime().toLocalDate();
        if(!request.getScreeningDate().equals(localDateStart))
            throw new RuntimeException("Movie show date and movie start time must be the same day");

        LocalDate curentLocalDate = LocalDate.now();
        if(curentLocalDate.isAfter(request.getScreeningDate())
                || curentLocalDate.isAfter(localDateStart)
        ){
            throw new RuntimeException(("Show date must be greater than or equal to today's date"));
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        if(ChronoUnit.MINUTES.between(currentDateTime, request.getStartTime()) < TIME_BEFORE_START){
            throw new RuntimeException(
                    String.format("You must make an schedule at least %d hour in advance", TIME_BEFORE_START));
        }

        LocalDateTime timeEndMovie = request.getEndTime().plusMinutes(timeMovie + 10); // them 10p t gian cho
        if(request.getStartTime().isAfter(request.getEndTime())
                && timeEndMovie.isAfter(request.getEndTime()))
            throw new RuntimeException(("The start time must be less than the end time " +
                    "and the total time must be equal to the movie viewing time plus 10 minutes of waiting time."));
    }

    private void checkOverlappingScreenings(ScheduleRequest request){
        if(scheduleRepository.findOverlappingScreenings
                (request.getStartTime(), request.getEndTime(), request.getCinemaRoomId()) > 0
        ){
            throw new RuntimeException(("The calendar already exists"));
        }
    }
}
