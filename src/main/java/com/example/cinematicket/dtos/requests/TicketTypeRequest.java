package com.example.cinematicket.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketTypeRequest {
    @NotBlank(message = "NAME_NOT_BLANK")
    @Size(min = 1, max = 100, message = "NAME_VALID")
    String name;

    @JsonProperty("ticket_price")
    @NotNull(message = "PRICE_NOT_NULL")
    @Positive(message = "PRICE_INVALID")
    double ticketPrice;

    String description;

    @DecimalMin(value = "0.0", inclusive = true, message = "Discount must be greater than or equal to 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "Discount must be less than or equal to 100")
    float discount ;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "^(ACTIVE|INACTIVE|DISCONTINUED)$", message = "Status must be either ACTIVE, INACTIVE, or DISCONTINUED")
    String status;
}
