package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.PromotionRequest;
import com.example.cinematicket.dtos.responses.PromotionResponse;
import com.example.cinematicket.entities.Promotion;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PromotionMapper {
    Promotion toPromotion(PromotionRequest request);

    PromotionResponse toPromotionResponse(Promotion promotion);

    void updatePromotion(@MappingTarget Promotion promotion, PromotionRequest request);
}
