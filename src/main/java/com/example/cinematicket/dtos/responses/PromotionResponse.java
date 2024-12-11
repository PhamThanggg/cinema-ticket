package com.example.cinematicket.dtos.responses;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionResponse {
    String id;

    String name;

    String description;

    int discount;

    int count;

    String promotionType;

    String discountType;

    Integer min;

    Integer max;

    LocalDate startDate;

    LocalDate endDate;
}
