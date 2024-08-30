package com.example.cinematicket.services.Area;

import com.example.cinematicket.dtos.requests.AreaRequest;
import com.example.cinematicket.dtos.responses.AreaResponse;
import com.example.cinematicket.entities.Area;
import com.example.cinematicket.mapper.AreaMapper;
import com.example.cinematicket.repositories.AreaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AreaService implements IAreaService{
    AreaRepository areaRepository;
    AreaMapper areaMapper;
    @Override
    public AreaResponse createArea(AreaRequest request) {
        if(areaRepository.existsByAreaName(request.getAreaName())){
            throw  new RuntimeException("Area exists");
        }

        Area area = areaMapper.toArea(request);
        return areaMapper.toAreaResponse(areaRepository.save(area));
    }

    @Override
    public List<AreaResponse> getAreaALl() {
        return areaRepository.findAll().stream().map(areaMapper::toAreaResponse).toList();
    }

    @Override
    public AreaResponse updateArea(AreaRequest request, Long id) {
        Area area = areaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Area not exists"));

        if(!area.getAreaName().equals(request.getAreaName())){
            if(areaRepository.existsByAreaName(request.getAreaName())){
                throw  new RuntimeException("Area exists");
            }
        }

        areaMapper.updateArea(area, request);
        return areaMapper.toAreaResponse(areaRepository.save(area));
    }

    @Override
    public void deleteArea(Long id) {
        areaRepository.deleteById(id);
    }
}
