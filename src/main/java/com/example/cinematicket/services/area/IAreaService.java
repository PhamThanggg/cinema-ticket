package com.example.cinematicket.services.area;

import com.example.cinematicket.dtos.requests.AreaRequest;
import com.example.cinematicket.dtos.responses.AreaResponse;

import java.util.List;

public interface IAreaService {
    AreaResponse createArea(AreaRequest request);

    List<AreaResponse> getAreaALl();

    AreaResponse updateArea(AreaRequest request, Long id);

    void deleteArea(Long id);
}
