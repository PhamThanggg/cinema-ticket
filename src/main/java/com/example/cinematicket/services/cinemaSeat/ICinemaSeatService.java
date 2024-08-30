package com.example.cinematicket.services.cinemaSeat;

import com.example.cinematicket.dtos.requests.CinemaSeatRequest;
import com.example.cinematicket.dtos.responses.CinemaSeatResponse;
import org.springframework.data.domain.Page;

public interface ICinemaSeatService {
    CinemaSeatResponse createCinemaSeat(CinemaSeatRequest request);
    CinemaSeatResponse getCinemaSeatById(Long id);
    Page<CinemaSeatResponse> getCinemaSeat(int page, int limit, Long cinemaRoomId);
    Page<CinemaSeatResponse> searchCinemaSeat(String name, int page, int limit);
    CinemaSeatResponse updateCinemaSeat(Long id, CinemaSeatRequest request);
    void deleteCinemaSeat(Long id);
}
