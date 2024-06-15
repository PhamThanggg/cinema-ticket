package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.RoomTypeRequest;
import com.example.cinematicket.dtos.responses.RoomTypeResponse;
import com.example.cinematicket.entities.RoomType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomTypeMapper {
    RoomType toRoomType(RoomTypeRequest request);
    RoomTypeResponse toRoomTypeResponse(RoomType roomType);
    void updateRoomType(@MappingTarget RoomType roomType, RoomTypeRequest request);
}
