package com.example.cinematicket.services.cinemaSeat;

import com.example.cinematicket.dtos.requests.seat.CinemaSeatRequest;
import com.example.cinematicket.dtos.responses.SeatReservationResponse;
import com.example.cinematicket.dtos.responses.SeatTypeResponse;
import com.example.cinematicket.dtos.responses.cinemaSeat.CinemaSeatResponse;
import com.example.cinematicket.dtos.responses.UserResponse;
import com.example.cinematicket.entities.CinemaRoom;
import com.example.cinematicket.entities.CinemaSeat;
import com.example.cinematicket.entities.SeatType;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.CinemaSeatMapper;
import com.example.cinematicket.repositories.CinemaRoomRepository;
import com.example.cinematicket.repositories.CinemaSeatRepository;
import com.example.cinematicket.repositories.SeatTypeRepository;
import com.example.cinematicket.services.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CinemaSeatService implements ICinemaSeatService {
    CinemaSeatRepository cinemaSeatRepository;
    SeatTypeRepository seatTypeRepository;
    CinemaRoomRepository cinemaRoomRepository;
    CinemaSeatMapper cinemaSeatMapper;
    UserService userService;
    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_SEAT')")
    public CinemaSeatResponse createCinemaSeat(CinemaSeatRequest request) {
        if(cinemaSeatRepository.existsByCinemaRoomIdAndName(request.getCinemaRoomId(), request.getName()))
            throw new AppException(ErrorCode.CINEMA_SEAT_EXISTED);

        if(cinemaSeatRepository.existsByCinemaRoomIdAndRowAndColum(request.getCinemaRoomId(), request.getRow(), request.getColum()))
            throw new AppException(ErrorCode.CINEMA_SEAT_INDEX_EXISTED);

        SeatType seatType = seatTypeRepository.findById(request.getSeatTypeId()).
                orElseThrow(()-> new AppException(ErrorCode.SEAT_TYPE_NOT_EXISTED));

        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(request.getCinemaRoomId()).
                orElseThrow(()-> new AppException(ErrorCode.CINEMA_ROOM_NOT_EXISTED));

        CinemaSeat cinemaSeat = cinemaSeatMapper.toCinemaSeat(request);
        cinemaSeat.setSeatType(seatType);
        cinemaSeat.setCinemaRoom(cinemaRoom);
        cinemaSeatRepository.save(cinemaSeat);
        return cinemaSeatMapper.toCinemaSeatResponse(cinemaSeatRepository.save(cinemaSeat));
    }

    public List<CinemaSeatResponse> addSeatsAutomatic(Long cinemaRoomId, Long seatTypeId, int row, int column) {
        if(cinemaSeatRepository.countByCinemaRoomId(cinemaRoomId) > 0){
            throw new AppException(ErrorCode.SEAT_COUNT);
        }
        SeatType seatType = seatTypeRepository.findById(seatTypeId).
                orElseThrow(()-> new AppException(ErrorCode.SEAT_TYPE_NOT_EXISTED));

        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(cinemaRoomId).
                orElseThrow(()-> new AppException(ErrorCode.CINEMA_ROOM_NOT_EXISTED));

        List<CinemaSeat> cinemaSeats = new ArrayList<>();

        for (int i = 0; i < row; i++) {
            String rowName = String.valueOf((char) ('A' + i));

            for (int j = 1; j <= column; j++) {
                CinemaSeat seat = new CinemaSeat();
                seat.setName(rowName + j);
                seat.setCinemaRoom(cinemaRoom);
                seat.setSeatType(seatType);
                seat.setRow(rowName);
                seat.setColum(String.valueOf(j));
                seat.setStatus(1);

                cinemaSeats.add(seat);
            }
        }

        List<CinemaSeat> Seats = cinemaSeatRepository.saveAll(cinemaSeats);
        return Seats.stream()
                .map(cinemaSeatMapper::toCinemaSeatResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CinemaSeatResponse getCinemaSeatById(Long id) {
        CinemaSeat seat = cinemaSeatRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.CINEMA_SEAT_NOT_EXISTED));

        return cinemaSeatMapper.toCinemaSeatResponse(seat);
    }

    @Override
    public List<CinemaSeatResponse> getCinemaSeat(Long scheduleId) {
        List<CinemaSeat> cinemaSeats = cinemaSeatRepository.findBySeatCinemaRoomId(scheduleId);

        return cinemaSeats.stream()
                .map(cinemaSeatMapper::toCinemaSeatResponse)
                .collect(Collectors.toList());
    }

    public List<CinemaSeatResponse> getCinemaSeat(Long scheduleId, int status) {
        LocalDateTime timeNow = LocalDateTime.now();
        List<Object[]> results  = cinemaSeatRepository.findBySeatBookedNative(scheduleId, status, timeNow);

        return mapCinemaSeatResponse(results);
    }

    public List<CinemaSeatResponse> getCinemaSeatBought(Long scheduleId, int status) {
        List<Object[]> results  = cinemaSeatRepository.findBySeatBoughtNative(scheduleId, status);
        return mapCinemaSeatResponse(results);
    }

    @Override
    public List<CinemaSeatResponse> cinemaSeatByRoom(Long roomId) {
        List<CinemaSeat> cinemaSeats = cinemaSeatRepository.findByCinemaRoomId(roomId);

        return cinemaSeats.stream()
                .map(cinemaSeatMapper::toCinemaSeatResponse)
                .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_SEAT')")
    public CinemaSeatResponse updateCinemaSeat(Long id, CinemaSeatRequest request) {
        CinemaSeat seat = cinemaSeatRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.CINEMA_SEAT_NOT_EXISTED));

        if(!seat.getName().equals(request.getName())){
            if(cinemaSeatRepository.existsByCinemaRoomIdAndName(request.getCinemaRoomId(), request.getName()))
                throw new AppException(ErrorCode.CINEMA_SEAT_EXISTED);
        }

        if(!seat.getRow().equals(request.getRow()) ||
                !seat.getColum().equals(request.getColum())){
            if(cinemaSeatRepository.existsByCinemaRoomIdAndRowAndColum(
                    request.getCinemaRoomId(), request.getRow(), request.getColum()))
                throw new AppException(ErrorCode.CINEMA_SEAT_INDEX_EXISTED);
        }

        SeatType seatType = seatTypeRepository.findById(request.getSeatTypeId()).
                orElseThrow(()-> new AppException(ErrorCode.SEAT_TYPE_NOT_EXISTED));

        cinemaSeatMapper.updateCinemaSeat(seat, request);
        seat.setName(request.getName());
        seat.setStatus(request.getStatus());
        seat.setColum(request.getColum());
        seat.setRow(request.getRow());
        seat.setSeatType(seatType);
        return cinemaSeatMapper.toCinemaSeatResponse(cinemaSeatRepository.save(seat));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_SEAT')")
    public void deleteCinemaSeat(Long id) {
        cinemaSeatRepository.deleteById(id);
    }

    @Override
    public boolean isValidSeatSelection(List<Long> selectedSeats, List<Long> rowSeats) {
        for (Long seat : selectedSeats) {
            // Kiểm tra ghế bên trái
            if (seat > 1 && rowSeats.contains(seat - 1) && !selectedSeats.contains(seat - 1)) {
                // Ghế bên trái bị bỏ trống đơn lẻ
                return false;
            }
            // Kiểm tra ghế bên phải
            if (seat < rowSeats.size() && rowSeats.contains(seat + 1) && !selectedSeats.contains(seat + 1)) {
                // Ghế bên phải bị bỏ trống đơn lẻ
                return false;
            }
        }
        return true;
    }

    public List<CinemaSeatResponse> mapCinemaSeatResponse(List<Object[]> results){
        List<CinemaSeatResponse> seatResponses = new ArrayList<>();
        for (Object[] objects : results){
            SeatReservationResponse seatReservationResponse = SeatReservationResponse.builder()
                    .id((Long) objects[6])
                    .userId((Long) objects[7])
                    .status((Integer) objects[9])
                    .reservationTime(((Timestamp) objects[10]).toLocalDateTime())
                    .expiryTime(((Timestamp) objects[11]).toLocalDateTime())
                    .build();

            SeatType seatType = SeatType.builder()
                    .id((Long) objects[12])
                    .name((String) objects[13])
                    .price((Double) objects[14])
                    .build();

            CinemaSeatResponse cinemaSeatResponse = CinemaSeatResponse.builder()
                    .id((Long) objects[0])
                    .name((String) objects[1])
                    .row((String) objects[2])
                    .colum((String) objects[3])
                    .status((Integer) objects[4])
                    .cinemaRoomId((Long) objects[5])
                    .seatReservations(seatReservationResponse)
                    .seatType(seatType)
                    .build();
            seatResponses.add(cinemaSeatResponse);
        }
        return seatResponses;
    }

    public List<CinemaSeatResponse> mapCinemaSeatDeleteResponse(List<Object[]> results){
        List<CinemaSeatResponse> seatResponses = new ArrayList<>();
        for (Object[] objects : results){
            SeatType seatType = SeatType.builder()
                    .id((Long) objects[12])
                    .name((String) objects[13])
                    .price((Double) objects[14])
                    .build();

            CinemaSeatResponse cinemaSeatResponse = CinemaSeatResponse.builder()
                    .id((Long) objects[0])
                    .name((String) objects[1])
                    .row((String) objects[2])
                    .colum((String) objects[3])
                    .status((Integer) objects[4])
                    .cinemaRoomId((Long) objects[5])
                    .seatReservations(null)
                    .seatType(seatType)
                    .build();
            seatResponses.add(cinemaSeatResponse);
        }
        return seatResponses;
    }
}
