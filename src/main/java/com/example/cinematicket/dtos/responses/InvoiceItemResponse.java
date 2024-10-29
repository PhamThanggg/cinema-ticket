package com.example.cinematicket.dtos.responses;

import com.example.cinematicket.dtos.responses.item.ItemResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceItemResponse {
    ItemResponse item;
    int quantity;
}
