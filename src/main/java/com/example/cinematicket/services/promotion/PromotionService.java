package com.example.cinematicket.services.promotion;


import com.example.cinematicket.dtos.requests.PromotionRequest;
import com.example.cinematicket.dtos.responses.PromotionResponse;
import com.example.cinematicket.entities.Promotion;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.PromotionMapper;
import com.example.cinematicket.repositories.PromotionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PromotionService {
    PromotionRepository promotionRepository;
    PromotionMapper promotionMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public PromotionResponse createPromotion(PromotionRequest request) {
        if(promotionRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.PROMOTION_NOT_EXISTS);
        }

        Promotion promotion = promotionMapper.toPromotion(request);
        return promotionMapper.toPromotionResponse(promotionRepository.save(promotion));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Page<PromotionResponse> getPromotionALl(String name, int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));

        return promotionRepository.findByPromotionOrName(name, pageable).map(promotionMapper::toPromotionResponse);
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
    public void deletePromotion(String id) {
        promotionRepository.deleteById(id);
    }
}
