package com.example.cinematicket.controllers;

import com.example.cinematicket.dtos.requests.RoomTypeRequest;
import com.example.cinematicket.dtos.responses.ApiResponse;
import com.example.cinematicket.dtos.responses.RoomTypeResponse;
import com.example.cinematicket.services.cinemaRoom.RoomTypeService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("${api.prefix}/room_type")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoomTypeController {
    RoomTypeService roomTypeService;

    @PostMapping("")
    ApiResponse<RoomTypeResponse> createRoomType(@RequestBody @Valid RoomTypeRequest request){
        return ApiResponse.<RoomTypeResponse>builder()
                .message("Create successfully")
                .result(roomTypeService.createRoomType(request))
                .build();
    }

    @GetMapping("")
    ApiResponse<List<RoomTypeResponse>> getRoomType(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        return  ApiResponse.<List<RoomTypeResponse>>builder()
//                .message("Create successfully")
                .result(roomTypeService.getRoomTypes(page, limit).getContent())
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<RoomTypeResponse> getRoomTypeById(
            @PathVariable("id") Long id
    ){
        return ApiResponse.<RoomTypeResponse>builder()
//                .message("Create successfully")
                .result(roomTypeService.getRoomTypeById(id))
                .build();
    }

    @GetMapping("/search")
    ApiResponse<List<RoomTypeResponse>> SearchRoomType(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit,
            @RequestParam("name") String name
    ){
        return  ApiResponse.<List<RoomTypeResponse>>builder()
//                .message("Create successfully")
                .result(roomTypeService.searchRoomType(name, page, limit).getContent())
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<RoomTypeResponse> updateRoomType(
            @PathVariable("id") Long id,
            @RequestBody RoomTypeRequest request
    ){
        return ApiResponse.<RoomTypeResponse>builder()
                .message("Update successfully")
                .result(roomTypeService.updateRoomType(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<RoomTypeResponse> deleteRoomType(
            @PathVariable("id") Long id
    ){
        roomTypeService.deleteRoomType(id);
        return ApiResponse.<RoomTypeResponse>builder()
                .message("Delete successfully")
                .build();
    }
}
