package com.example.cinematicket.dtos.requests.vnpay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VNPayRequest {
    String orderId;
    int amount;
    int timePay;
    String orderInfo;
    String orderType;
    String returnUrl;
}
