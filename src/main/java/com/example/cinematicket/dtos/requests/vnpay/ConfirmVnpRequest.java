package com.example.cinematicket.dtos.requests.vnpay;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConfirmVnpRequest {
    String secureHash;
    Long amount;
    String transactionNo;
    String responseCode;
    String txnRef;
}
