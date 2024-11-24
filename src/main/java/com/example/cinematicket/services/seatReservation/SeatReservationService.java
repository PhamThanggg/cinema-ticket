package com.example.cinematicket.services.seatReservation;

import com.example.cinematicket.dtos.requests.seat.SeatReservationRequest;
import com.example.cinematicket.dtos.responses.cinemaSeat.CinemaSeatResponse;
import com.example.cinematicket.entities.CinemaSeat;
import com.example.cinematicket.entities.Schedule;
import com.example.cinematicket.entities.SeatReservation;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.CinemaSeatMapper;
import com.example.cinematicket.repositories.CinemaSeatRepository;
import com.example.cinematicket.repositories.ScheduleRepository;
import com.example.cinematicket.repositories.SeatReservationRepository;
import com.example.cinematicket.repositories.UserRepository;
import com.example.cinematicket.services.cinemaSeat.CinemaSeatService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SeatReservationService  {
    SeatReservationRepository seatReservationRepository;
    CinemaSeatRepository cinemaSeatRepository;
    ScheduleRepository scheduleRepository;
    UserRepository userRepository;
    SimpMessagingTemplate messagingTemplate;
    CinemaSeatMapper cinemaSeatMapper;
    CinemaSeatService cinemaSeatService;

    @Transactional
    public List<CinemaSeatResponse> createSeatReservation(SeatReservationRequest request) {
        Schedule schedule = scheduleRepository.findById(request.getScheduleId())
                .orElseThrow(() -> new AppException(ErrorCode.SCHEDULE_NOT_EXISTS));

        if(!userRepository.existsById(request.getUserId())){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        Set<Long> seatIds = request.getSeatIds();

        List<CinemaSeat> foundSeats = cinemaSeatRepository.findAllById(seatIds);

        Set<Long> foundSeatIds = foundSeats.stream()
                .map(CinemaSeat::getId)
                .collect(Collectors.toSet());

        Set<Long> missingSeatIds = seatIds.stream()
                .filter(seatId -> !foundSeatIds.contains(seatId))
                .collect(Collectors.toSet());

        if (!missingSeatIds.isEmpty()) {
            throw new AppException(ErrorCode.CINEMA_SEAT_NOT_EXISTED, missingSeatIds.toString());
        }

        LocalDateTime expiryTime = LocalDateTime.now();
        Set<Long> ids = seatReservationRepository.findBookedSeatsByScheduleIdAndSeatIds(request.getScheduleId(), seatIds, expiryTime);

        if(!ids.isEmpty()){
            throw new AppException(ErrorCode.CINEMA_SEAT_EXISTED, ids.toString());
        }

        List<SeatReservation> seatReservations = IntStream.range(0, seatIds.size())
                .mapToObj(i -> {
                    SeatReservation seatReservation = new SeatReservation();
                    seatReservation.setUserId(request.getUserId());
                    seatReservation.setCinemaSeat(foundSeats.get(i));
                    seatReservation.setSchedule(schedule);
                    seatReservation.setStatus(request.getStatus());
                    seatReservation.setReservationTime(request.getReservationTime());
                    seatReservation.setExpiryTime(request.getExpiryTime());
                    return seatReservation;
                })
                .toList();

        seatReservationRepository.saveAll(seatReservations);
        return notifySeatUpdate(request.getSeatIds());
    }

    @PreAuthorize("hasRole('ADMIN') or @securityService.isSeatReservationOwner(#seatIds, authentication.principal.getClaimAsString('id'))")
    public List<CinemaSeatResponse> updateStatus(Set<Long> seatIds){
        seatReservationRepository.updateStatusByIds(seatIds);
        return notifySeatUpdate(seatIds);
    }

    @PreAuthorize("hasRole('ADMIN') or @securityService.isSeatReservationOwner(#cinemaSeatId, #scheduleId, #expiryTime, authentication.principal.getClaimAsString('id'))")
    @Transactional
    public void deleteSeatReservations(Long cinemaSeatId, Long scheduleId,  LocalDateTime expiryTime) {
        Set<Long> ids = new HashSet<>();
        ids.add(cinemaSeatId);
        List<Object[]> responses = cinemaSeatRepository.findBySeatInID(ids);
        List<CinemaSeatResponse> seatReservationResponse = cinemaSeatService.mapCinemaSeatDeleteResponse(responses);

        seatReservationRepository.deleteByCinemaSeatId(cinemaSeatId, scheduleId, expiryTime);

        notifySeatDelet(seatReservationResponse);
    }

    public List<CinemaSeatResponse> notifySeatUpdate(Set<Long> seatIds) {

//        List<CinemaSeat> seats = cinemaSeatRepository.findAllById(seatIds);
//
//        if (seats.isEmpty()) {
//            throw new AppException(ErrorCode.CINEMA_SEAT_NOT_EXISTED);
//        }
//
//        List<CinemaSeatResponse> seatResponses = cinemaSeatMapper.toCinemaSeatResponse(seats);

        List<Object[]> responses = cinemaSeatRepository.findBySeatInID(seatIds);
        List<CinemaSeatResponse> seatReservationResponse = cinemaSeatService.mapCinemaSeatResponse(responses);
        messagingTemplate.convertAndSend("/topic/seats", seatReservationResponse);
        return seatReservationResponse;
    }

    public List<CinemaSeatResponse> notifySeatDelet(List<CinemaSeatResponse> seatReservationResponse) {

        messagingTemplate.convertAndSend("/topic/seats", seatReservationResponse);
        return seatReservationResponse;
    }

}
