package com.example.cinematicket.dtos.requests;

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

    @Size(max = 255, message = "Mô tả không được vượt quá 255 ký tự")
    private String description;

    @Min(value = 0, message = "DISCOUNT_MIN")
    @Max(value = 100, message = "DISCOUNT_MAX")
    @NotNull(message = "DISCOUNT")
    private int discount;

    @Min(value = 0, message = "Số lượng không được âm")
    private int count;

    String promotionType;

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
