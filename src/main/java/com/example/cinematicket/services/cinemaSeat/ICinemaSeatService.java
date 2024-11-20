package com.example.cinematicket.services.cinemaSeat;

import com.example.cinematicket.dtos.requests.seat.CinemaSeatRequest;
import com.example.cinematicket.dtos.responses.cinemaSeat.CinemaSeatResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICinemaSeatService {
    CinemaSeatResponse createCinemaSeat(CinemaSeatRequest request);
    CinemaSeatResponse getCinemaSeatById(Long id);
    List<CinemaSeatResponse> getCinemaSeat(Long scheduleId);
    List<CinemaSeatResponse> cinemaSeatByRoom(Long roomId);
    CinemaSeatResponse updateCinemaSeat(Long id, CinemaSeatRequest request);
    void deleteCinemaSeat(Long id);

    boolean isValidSeatSelection(List<Long> selectedSeats, List<Long> rowSeats);
}
