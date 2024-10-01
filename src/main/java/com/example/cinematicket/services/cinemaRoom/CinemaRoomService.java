package com.example.cinematicket.services.cinemaRoom;

import com.example.cinematicket.dtos.requests.CinemaRoomRequest;
import com.example.cinematicket.dtos.responses.CinemaRoomResponse;
import com.example.cinematicket.entities.Cinema;
import com.example.cinematicket.entities.CinemaRoom;
import com.example.cinematicket.entities.RoomType;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.CinemaRoomMapper;
import com.example.cinematicket.repositories.CinemaRepository;
import com.example.cinematicket.repositories.CinemaRoomRepository;
import com.example.cinematicket.repositories.RoomTypeRepository;
import com.example.cinematicket.services.cinemaRoom.ICinemaRoomService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CinemaRoomService implements ICinemaRoomService {
    CinemaRoomRepository cinemaRoomRepository;
    CinemaRepository cinemaRepository;
    RoomTypeRepository roomTypeRepository;
    CinemaRoomMapper cinemaRoomMapper;
    @Override
    @PostAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_ROOM')")
    public CinemaRoomResponse createCinemaRoom(CinemaRoomRequest request) {
        if(cinemaRoomRepository.existsByName(request.getName()))
            throw new AppException(ErrorCode.CINEMA_ROOM_EXISTED);

        Cinema cinema = cinemaRepository.findById(request.getCinemaId()).
                orElseThrow(()->new AppException(ErrorCode.CINEMA_NOT_EXISTED));

        RoomType roomType = roomTypeRepository.findById(request.getRoomTypeId()).
                orElseThrow(()->new AppException(ErrorCode.ROOM_TYPE_NOT_EXISTED));

        CinemaRoom cinemaRoom = cinemaRoomMapper.toCinemaRoom(request);
        cinemaRoom.setCinema(cinema);
        cinemaRoom.setRoomType(roomType);
        return cinemaRoomMapper.toCinemaRoomResponse(cinemaRoomRepository.save(cinemaRoom));
    }

    @Override
    public CinemaRoomResponse getCinemaRoomById(Long id) {
        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(id).
                orElseThrow(()-> new AppException(ErrorCode.CINEMA_ROOM_NOT_EXISTED));
        return cinemaRoomMapper.toCinemaRoomResponse(cinemaRoom);
    }

    @Override
    public Page<CinemaRoomResponse> getAllCinemaRoom(int page, int limit, Long cinema_id) {
        Pageable pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC, "id"));

        return cinemaRoomRepository
                .findByCinemaId(cinema_id, pageRequest)
                .map(cinemaRoomMapper::toCinemaRoomResponse);
    }

    @Override
    public Page<CinemaRoomResponse> searchCinemaRoom(String name, Long cinema_id, int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC, "id"));

        Page<CinemaRoomResponse> cinemaRoomResponses;
        if(name==null){
            cinemaRoomResponses = cinemaRoomRepository
                    .findByCinemaId(cinema_id, pageRequest)
                    .map(cinemaRoomMapper::toCinemaRoomResponse);
        }else{
            cinemaRoomResponses = cinemaRoomRepository
                    .findByNameContainingAndCinemaId(name, cinema_id, pageRequest)
                    .map(cinemaRoomMapper::toCinemaRoomResponse);
        }

        return cinemaRoomResponses;
    }

    @Override
    public Long totalCinemaRoom(Long cinemaId) {
        return cinemaRoomRepository.countByCinemaId(cinemaId);
    }

    @Override
    public Long totalCinemaRoomSearch(String name) {
        return cinemaRoomRepository.countByCinemaName(name);
    }

    @Override
    @PostAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_ROOM')")
    public CinemaRoomResponse updateCinemaRoom(Long id, CinemaRoomRequest request) {
        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.CINEMA_ROOM_NOT_EXISTED));

        Cinema cinema = cinemaRepository.findById(request.getCinemaId()).
                orElseThrow(()->new AppException(ErrorCode.CINEMA_NOT_EXISTED));

        RoomType roomType = roomTypeRepository.findById(request.getRoomTypeId()).
                orElseThrow(()->new AppException(ErrorCode.ROOM_TYPE_NOT_EXISTED));

        cinemaRoomMapper.updateCinemaRoom(cinemaRoom, request);
        cinemaRoom.setCinema(cinema);
        cinemaRoom.setRoomType(roomType);
        return cinemaRoomMapper.toCinemaRoomResponse(cinemaRoomRepository.save(cinemaRoom));
    }

    @Override
    @PostAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_ROOM')")
    public void deleteCinemaRoom(Long id) {
        cinemaRoomRepository.deleteById(id);
    }
}
