package com.example.cinematicket.controllers;

import com.example.cinematicket.dtos.requests.vnpay.VNPayRequest;
import com.example.cinematicket.dtos.responses.ApiResponse;
import com.example.cinematicket.dtos.responses.InvoiceResponse;
import com.example.cinematicket.dtos.responses.TicketResponse;
import com.example.cinematicket.entities.Invoice;
import com.example.cinematicket.entities.Promotion;
import com.example.cinematicket.entities.User;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.repositories.InvoiceRepository;
import com.example.cinematicket.repositories.PromotionRepository;
import com.example.cinematicket.repositories.UserRepository;
import com.example.cinematicket.services.invoice.InvoiceService;
import com.example.cinematicket.services.momo.VNPayService;
import com.example.cinematicket.services.seatReservation.SeatReservationService;
import com.example.cinematicket.services.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("${api.prefix}/payment")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class PaymentController {
    VNPayService vnPayService;
    UserService userService;
    UserRepository userRepository;
    PromotionRepository promotionRepository;
    InvoiceRepository invoiceRepository;
    InvoiceService invoiceService;
    SeatReservationService seatReservationService;

    @PostMapping("/create")
    public ResponseEntity<?> createPayment(@RequestBody VNPayRequest request
    ) {
        var paymentUrl = vnPayService.createPayment(
                request.getOrderId(), request.getAmount(),
                request.getOrderInfo(), request.getReturnUrl(),
                request.getTimePay()
        );
        return ResponseEntity.ok(paymentUrl);
    }

    @PostMapping("vnpay-callback")
    @Transactional
    public ApiResponse<String> paymentCompleted(@RequestBody Map<String, String> requestData){
        int paymentStatus = vnPayService.orderReturn(requestData);

        Long orderId = Long.parseLong(requestData.get("vnp_TxnRef"));
        LocalDateTime paymentTime = LocalDateTime.now();
        String transactionId = requestData.get("vnp_TransactionNo");
        Double totalPrice = Double.parseDouble(requestData.get("vnp_Amount")) / 100;
        String status = paymentStatus == 1 ? "1" : "0";

        Invoice invoice = invoiceRepository.findById(orderId).
                orElseThrow(() -> new RuntimeException("Invoice not exists"));
        User user = userService.getMyInfoUser();

        if(invoice.getUser().getId() != user.getId()){
            throw new RuntimeException("Bạn không có quyền sửa thanh toán");
        }
        if(paymentStatus == 1 && invoice.getStatus() == 0){
           InvoiceResponse invoiceResponse = invoiceService.updateInvoice(orderId, paymentTime, totalPrice, 1);

            Set<Long> seatIds = new HashSet<>();
            for (TicketResponse data : invoiceResponse.getTickets()){
                seatIds.add(data.getCinemaSeat().getId());
            }
            seatReservationService.updateStatus(seatIds);

            if(invoiceResponse.getStar() != null){
                user.setStar(user.getStar() - invoiceResponse.getStar());
                userRepository.save(user);
            }

            if(invoiceResponse.getPromotion() != null){
                Promotion promotion = promotionRepository.findById(invoiceResponse.getPromotion().getId())
                        .orElseThrow(() -> new RuntimeException("Lỗi vui lòng thử lại sau"));
                promotion.setCount(promotion.getCount() - 1);
                promotionRepository.save(promotion);
            }

        }

        return ApiResponse.<String>builder()
                .result(status)
                .build();
    }

}
