package com.example.cinematicket.services.area;

import com.example.cinematicket.dtos.requests.AreaRequest;
import com.example.cinematicket.dtos.responses.AreaResponse;
import com.example.cinematicket.dtos.responses.GenreResponse;
import com.example.cinematicket.entities.Area;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.AreaMapper;
import com.example.cinematicket.repositories.AreaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AreaService implements IAreaService{
    AreaRepository areaRepository;
    AreaMapper areaMapper;
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public AreaResponse createArea(AreaRequest request) {
        if(areaRepository.existsByAreaName(request.getAreaName())){
            throw new AppException(ErrorCode.AREA_EXISTS);
        } // kiểm tra tên đã tồn tại trong CSDL chưa

        Area area = areaMapper.toArea(request);
        return areaMapper.toAreaResponse(areaRepository.save(area));
    }

    @Override
    public List<AreaResponse> getAreaALl() {
        return areaRepository.findAll().stream().map(areaMapper::toAreaResponse).toList();
    }

    public Page<AreaResponse> searchArea(String search, int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));

        return areaRepository.findAreaOrName(search, pageable).map(areaMapper::toAreaResponse);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public AreaResponse updateArea(AreaRequest request, Long id) {
        Area area = areaRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.AREA_NOT_EXISTS));

        if(!area.getAreaName().equals(request.getAreaName())){
            if(areaRepository.existsByAreaName(request.getAreaName())){
                throw  new AppException(ErrorCode.AREA_EXISTS);
            }
        }

        areaMapper.updateArea(area, request);
        return areaMapper.toAreaResponse(areaRepository.save(area));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteArea(Long id) {
        areaRepository.deleteById(id);
    }
}
