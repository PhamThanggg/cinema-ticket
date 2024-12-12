package com.example.cinematicket.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceItemRequest {
    @NotNull(message = "Item is required")
    @Min(value = 1, message = "Item id must be greater than or equal to 1")
    @JsonProperty("item_id")
    @Max(value = Integer.MAX_VALUE, message = "VALUE_TOO_LARGE")
    Long id;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be greater than or equal to 1")
    @Max(value = Integer.MAX_VALUE, message = "VALUE_TOO_LARGE")
    int quantity;
}
