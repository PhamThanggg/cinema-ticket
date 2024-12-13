package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.promotion.PromotionInfoRequest;
import com.example.cinematicket.dtos.requests.promotion.PromotionRequest;
import com.example.cinematicket.dtos.responses.PromotionResponse;
import com.example.cinematicket.entities.Promotion;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PromotionMapper {
    Promotion toPromotion(PromotionRequest request);

    Promotion toPromotionInfo(PromotionInfoRequest request);

    PromotionResponse toPromotionResponse(Promotion promotion);

    void updatePromotion(@MappingTarget Promotion promotion, PromotionRequest request);

    void updatePromotionInfo(@MappingTarget Promotion promotion, PromotionInfoRequest request);
}
