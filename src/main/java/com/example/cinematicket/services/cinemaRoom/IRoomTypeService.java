package com.example.cinematicket.services.cinemaRoom;

import com.example.cinematicket.dtos.requests.RoomTypeRequest;
import com.example.cinematicket.dtos.responses.RoomTypeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IRoomTypeService {
    RoomTypeResponse createRoomType(RoomTypeRequest request);
    RoomTypeResponse getRoomTypeById(Long id);
    List<RoomTypeResponse> getRoomTypes();
    Page<RoomTypeResponse> searchRoomType(String name, int page, int limit);
    RoomTypeResponse updateRoomType(Long id, RoomTypeRequest request);
    void deleteRoomType(Long id);
    Long totalRoomType();
    Long totalRoomType(String name);
}
