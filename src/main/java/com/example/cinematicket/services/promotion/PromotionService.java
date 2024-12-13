package com.example.cinematicket.services.promotion;


import com.example.cinematicket.dtos.requests.promotion.PromotionInfoRequest;
import com.example.cinematicket.dtos.requests.promotion.PromotionRequest;
import com.example.cinematicket.dtos.responses.MovieImageResponse;
import com.example.cinematicket.dtos.responses.PromotionResponse;
import com.example.cinematicket.entities.Movie;
import com.example.cinematicket.entities.MovieImage;
import com.example.cinematicket.entities.Promotion;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.PromotionMapper;
import com.example.cinematicket.repositories.PromotionRepository;
import com.example.cinematicket.services.uploadFile.CloudService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PromotionService {
    PromotionRepository promotionRepository;
    PromotionMapper promotionMapper;
    CloudService cloudService;

    @PreAuthorize("hasRole('ADMIN')")
    public PromotionResponse createPromotion(PromotionRequest request) {
        if(promotionRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.PROMOTION_NOT_EXISTS);
        }

        Promotion promotion = promotionMapper.toPromotion(request);
        return promotionMapper.toPromotionResponse(promotionRepository.save(promotion));
    }


    @PreAuthorize("hasRole('ADMIN')")
    public PromotionResponse createPromotionInfo(PromotionInfoRequest request) {
        if(promotionRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.PROMOTION_NOT_EXISTS);
        }

        Promotion promotion = promotionMapper.toPromotionInfo(request);
        return promotionMapper.toPromotionResponse(promotionRepository.save(promotion));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Page<PromotionResponse> getPromotionALl(String name, String promotionType, Integer status, int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        LocalDate dateNow = null;
        LocalDate dateNow2 = null;
        if(status != null){
            if(status == 1){
                dateNow = LocalDate.now();
            }else{
                dateNow2 = LocalDate.now();
            }
        }
        return promotionRepository.findByPromotionOrName(name, promotionType, dateNow, dateNow2, pageable).map(promotionMapper::toPromotionResponse);
    }

    public Page<PromotionResponse> getPromotionALl(String name,  int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "startDate"));
        LocalDate dateNow = LocalDate.now();

        return promotionRepository.findByPromotionInfoOrName(name, dateNow, pageable).map(promotionMapper::toPromotionResponse);
    }

    public PromotionResponse getPromotionByName(String name) {
        Promotion promotion = promotionRepository.findValidByName(name)
                .orElseThrow(() -> new AppException(ErrorCode.PROMOTION_NOT_EXISTS));
        return promotionMapper.toPromotionResponse(promotion);
    }

    public PromotionResponse getPromotionById(String name) {
        Promotion promotion = promotionRepository.findById(name)
                .orElseThrow(() -> new AppException(ErrorCode.PROMOTION_NOT_EXISTS));
        return promotionMapper.toPromotionResponse(promotion);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public PromotionResponse updatePromotion(PromotionRequest request, String id) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.AREA_NOT_EXISTS));

        if(!promotion.getName().equals(request.getName())){
            if(promotionRepository.existsByName(request.getName())){
                throw new AppException(ErrorCode.AREA_EXISTS);
            }
        }

        promotionMapper.updatePromotion(promotion, request);
        return promotionMapper.toPromotionResponse(promotionRepository.save(promotion));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public PromotionResponse updatePromotionInfo(PromotionInfoRequest request, String id) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.AREA_NOT_EXISTS));

        if(!promotion.getName().equals(request.getName())){
            if(promotionRepository.existsByName(request.getName())){
                throw new AppException(ErrorCode.AREA_EXISTS);
            }
        }

        promotionMapper.updatePromotionInfo(promotion, request);
        return promotionMapper.toPromotionResponse(promotionRepository.save(promotion));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deletePromotion(String id) {
        promotionRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public PromotionResponse createPromotionImage(
            String promotionId,
            List<MultipartFile> files) throws IOException {

        if(files.size() > 1){
            throw new AppException(ErrorCode.IMAGE_MAX);
        }

        Promotion existingPromotion = promotionRepository
                .findById(promotionId)
                .orElseThrow(() -> new RuntimeException("Cannot find movie with id " + promotionId));

        String urlOld = existingPromotion.getImageUrl();
        List<Map> listPathImage = cloudService.uploadFiles(files);
        for(Map list : listPathImage){
            existingPromotion.setImageUrl(list.get("url").toString());
        }
        promotionRepository.save(existingPromotion);

        if(urlOld != null ){
            String publicId = cloudService.getPublicId(urlOld);
            cloudService.deleteImage(publicId);
        }
        return promotionMapper.toPromotionResponse(existingPromotion);
    }
}
