package com.example.cinematicket.services.cinemaSeat;

import com.example.cinematicket.dtos.requests.CinemaSeatRequest;
import com.example.cinematicket.dtos.responses.CinemaSeatResponse;
import com.example.cinematicket.entities.CinemaRoom;
import com.example.cinematicket.entities.CinemaSeat;
import com.example.cinematicket.entities.SeatType;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.CinemaSeatMapper;
import com.example.cinematicket.repositories.CinemaRoomRepository;
import com.example.cinematicket.repositories.CinemaSeatRepository;
import com.example.cinematicket.repositories.SeatTypeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CinemaSeatService implements ICinemaSeatService {
    CinemaSeatRepository cinemaSeatRepository;
    SeatTypeRepository seatTypeRepository;
    CinemaRoomRepository cinemaRoomRepository;
    CinemaSeatMapper cinemaSeatMapper;
    @Override
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

    @Override
    public CinemaSeatResponse getCinemaSeatById(Long id) {
        CinemaSeat seat = cinemaSeatRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.CINEMA_SEAT_NOT_EXISTED));

        return cinemaSeatMapper.toCinemaSeatResponse(seat);
    }

    @Override
    public Page<CinemaSeatResponse> getCinemaSeat(int page, int limit, Long cinemaRoomId) {
        Pageable pageable = PageRequest.of(page, limit);

        return cinemaSeatRepository.findByCinemaRoomId(cinemaRoomId, pageable).map(cinemaSeatMapper::toCinemaSeatResponse);
    }

    @Override
    public Page<CinemaSeatResponse> searchCinemaSeat(String name, int page, int limit) {
        return null;
    }

    @Override
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

        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(request.getCinemaRoomId()).
                orElseThrow(()-> new AppException(ErrorCode.CINEMA_ROOM_NOT_EXISTED));

        cinemaSeatMapper.updateCinemaSeat(seat, request);
        seat.setSeatType(seatType);
        seat.setCinemaRoom(cinemaRoom);
        return cinemaSeatMapper.toCinemaSeatResponse(cinemaSeatRepository.save(seat));
    }

    @Override
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
}
