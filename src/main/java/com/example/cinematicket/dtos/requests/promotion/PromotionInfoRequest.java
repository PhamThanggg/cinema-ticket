package com.example.cinematicket.dtos.requests.promotion;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionInfoRequest {
    @NotBlank(message = "PROMOTION_NAME_IS_REQUIRED")
    @Size(min = 1, max = 60, message = "PROMOTION_NAME_NOT_BLANK")
    private String name;

    @Size(max = 2000, message = "DESCRIPTION_INVALID")
    @NotBlank(message = "DESCRIPTION_NOT_BLANK")
    private String description;

    @NotBlank(message = "PROMOTION_TYPE")
    @Size(max = 20, message = "promotion type m 20 character")
    String promotionType;

    @NotNull(message = "START_DATE")
    private LocalDate startDate;

    @NotNull(message = "END_DATE")
    private LocalDate endDate;

    public boolean isValidDateRange() {
        return startDate != null && endDate != null && startDate.isBefore(endDate);
    }
}
