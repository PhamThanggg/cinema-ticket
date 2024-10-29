package com.example.cinematicket.dtos.responses.item;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceItemBookedResponse {
    ItemBookedResponse item;
    int quantity;
}
