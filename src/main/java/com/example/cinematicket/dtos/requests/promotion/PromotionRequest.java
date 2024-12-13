package com.example.cinematicket.dtos.requests.promotion;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionRequest {
    @NotBlank(message = "PROMOTION_NAME_IS_REQUIRED")
    @Size(min = 1, max = 60, message = "PROMOTION_NAME_NOT_BLANK")
    private String name;

    @Size(max = 2000, message = "DESCRIPTION_INVALID")
    private String description;

    @Max(value = Integer.MAX_VALUE, message = "DISCOUNT_MAX")
    @NotNull(message = "DISCOUNT")
    private int discount;

    @Min(value = 0, message = "COUNT_MIN")
    private int count;

    @NotBlank(message = "PROMOTION_TYPE")
    @Size(max = 20, message = "promotion type m 20 character")
    String promotionType;

    @NotBlank(message = "DISCOUNT_TYPE")
    @Size(max = 20, message = "discount  type m 20 character")
    String discountType;

    Integer min;

    Integer max;

    @NotNull(message = "START_DATE")
    private LocalDate startDate;

    @NotNull(message = "END_DATE")
    private LocalDate endDate;

    public boolean isValidDateRange() {
        return startDate != null && endDate != null && startDate.isBefore(endDate);
    }
}
