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
    @NotBlank(message = "Tên khuyến mãi không được để trống")
    @Size(min = 1, max = 60, message = "Tên khuyến mãi phải từ 1 đến 60 ký tự")
    private String name;

    @Size(max = 255, message = "Mô tả không được vượt quá 255 ký tự")
    private String description;

    @Min(value = 0, message = "Giảm giá không được âm")
    @Max(value = 100, message = "Giảm giá không được vượt quá 100%")
    private int discount;

    @Min(value = 0, message = "Số lượng không được âm")
    private int count;

    String promotionType;

    String discountType;

    Integer min;

    Integer max;

    @NotNull(message = "Ngày bắt đầu không được để trống")
    @FutureOrPresent(message = "Ngày bắt đầu phải là hôm nay hoặc tương lai")
    private LocalDate startDate;

    @NotNull(message = "Ngày kết thúc không được để trống")
    @Future(message = "Ngày kết thúc phải là tương lai")
    private LocalDate endDate;

    public boolean isValidDateRange() {
        return startDate != null && endDate != null && startDate.isBefore(endDate);
    }
}
