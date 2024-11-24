package com.example.cinematicket.controllers;

import com.example.cinematicket.dtos.requests.seat.SeatReservationRequest;
import com.example.cinematicket.dtos.responses.ApiResponse;
import com.example.cinematicket.dtos.responses.SeatReservationResponse;
import com.example.cinematicket.dtos.responses.cinemaSeat.CinemaSeatResponse;
import com.example.cinematicket.services.seatReservation.SeatReservationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("${api.prefix}/seat_reservation")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class SeatReservationController {
    SeatReservationService seatReservationService;

    @PostMapping("")
    public ApiResponse<List<CinemaSeatResponse>> create(
            @RequestBody @Valid SeatReservationRequest request
    ){

        return ApiResponse.<List<CinemaSeatResponse>>builder()
                .message("Create successfully")
                .result(seatReservationService.createSeatReservation(request))
                .build();
    }

    @DeleteMapping("/{id}/{scheduleId}")
    public ApiResponse<String> deleteSeatReservations(
            @PathVariable("id") Long id,
            @PathVariable("scheduleId") Long scheduleId

    ) {
        LocalDateTime expiryTime = LocalDateTime.now();
        seatReservationService.deleteSeatReservations(id, scheduleId, expiryTime);
        return ApiResponse.<String>builder()
                .result("SeatReservations have been deleted")
                .build();
    }
}
