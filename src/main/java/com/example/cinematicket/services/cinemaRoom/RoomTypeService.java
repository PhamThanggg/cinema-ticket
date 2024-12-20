package com.example.cinematicket.services.cinemaRoom;

import com.example.cinematicket.dtos.requests.RoomTypeRequest;
import com.example.cinematicket.dtos.responses.RoomTypeResponse;
import com.example.cinematicket.entities.RoomType;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.RoomTypeMapper;
import com.example.cinematicket.repositories.RoomTypeRepository;
import com.example.cinematicket.services.cinemaRoom.IRoomTypeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class RoomTypeService implements IRoomTypeService {
    RoomTypeRepository roomTypeRepository;
    RoomTypeMapper roomTypeMapper;
    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_ROOM')")
    public RoomTypeResponse createRoomType(RoomTypeRequest request) {
        if(roomTypeRepository.existsByName(request.getName()))
            throw new AppException(ErrorCode.ROOM_TYPE_EXISTED);

        RoomType roomType = roomTypeMapper.toRoomType(request);
        return roomTypeMapper.toRoomTypeResponse(roomTypeRepository.save(roomType));
    }

    @Override
    public RoomTypeResponse getRoomTypeById(Long id) {
        RoomType roomType = roomTypeRepository.findById(id).
                orElseThrow(()->new AppException(ErrorCode.ROOM_TYPE_NOT_EXISTED));

        return roomTypeMapper.toRoomTypeResponse(roomType);
    }

    @Override
    public List<RoomTypeResponse> getRoomTypes() {
        return roomTypeRepository.findAll().stream().map(roomTypeMapper::toRoomTypeResponse).toList();
    }

    @Override
    public Page<RoomTypeResponse> searchRoomType(String name, int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));

        Page<RoomTypeResponse> roomTypeResponses;
        if(name==null){
            roomTypeResponses = roomTypeRepository.findAll(pageRequest).map(roomTypeMapper::toRoomTypeResponse);
        }else{
            roomTypeResponses = roomTypeRepository.findByNameContaining(name, pageRequest).map(roomTypeMapper::toRoomTypeResponse);
        }
        return roomTypeResponses;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_ROOM')")
    public RoomTypeResponse updateRoomType(Long id, RoomTypeRequest request) {
        RoomType roomType = roomTypeRepository.findById(id).
                orElseThrow(()-> new AppException(ErrorCode.ROOM_TYPE_NOT_EXISTED));

        roomTypeMapper.updateRoomType(roomType, request);
        return roomTypeMapper.toRoomTypeResponse(roomTypeRepository.save(roomType));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_ROOM')")
    public void deleteRoomType(Long id) {
        roomTypeRepository.deleteById(id);
    }

    @Override
    public Long totalRoomType() {
        return roomTypeRepository.count();
    }

    @Override
    public Long totalRoomType(String name) {
        return roomTypeRepository.countByNameContaining(name);
    }
}
