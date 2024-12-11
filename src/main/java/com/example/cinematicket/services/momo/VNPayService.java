package com.example.cinematicket.services.momo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class VNPayService {
    @Value("${vnp.partnerCode}")
    private String partnerCode;
    @Value("${vnp.accessKey}")
    private String accessKey;
    @Value("${vnp.secretKey}")
    private String secretKey;
    @Value("${vnp.endpoint}")
    private String endpoint;

    // Thêm HttpServletRequest để lấy địa chỉ IP
    public ResponseEntity<?> createPayment(String orderId, int amount, String orderInfo, String returnUrl, int timePay) {
//        String vnp_TxnRef = getRandomNumber(8);
        String vnp_IpAddr = getIpAddress();
        int formattedAmount = amount * 100;

        Map<String, String> vnpParams = new HashMap<>();
        vnpParams.put("vnp_Version", "2.1.0");
        vnpParams.put("vnp_Command", "pay");
        vnpParams.put("vnp_TmnCode", partnerCode);
        vnpParams.put("vnp_Amount", String.valueOf(formattedAmount));
        vnpParams.put("vnp_CurrCode", "VND");
        vnpParams.put("vnp_TxnRef", orderId);
        vnpParams.put("vnp_OrderInfo", orderInfo);
        vnpParams.put("vnp_OrderType", "190001");
        vnpParams.put("vnp_ReturnUrl", returnUrl);
        vnpParams.put("vnp_IpAddr", vnp_IpAddr);
        vnpParams.put("vnp_BankCode", "");
        vnpParams.put("vnp_Locale", "vn");

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnpParams.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, timePay);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnpParams.put("vnp_ExpireDate", vnp_ExpireDate);

        // Tạo chuỗi hash và query string
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        vnpParams.keySet().stream().sorted().forEach(key -> {
            try {
                String value = vnpParams.get(key);
                if (value != null && !value.isEmpty()) {
                    hashData.append(key).append('=').append(URLEncoder.encode(value, StandardCharsets.US_ASCII.toString())).append('&');
                    query.append(URLEncoder.encode(key, StandardCharsets.US_ASCII.toString())).append('=').append(URLEncoder.encode(value, StandardCharsets.US_ASCII.toString())).append('&');
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
        hashData.deleteCharAt(hashData.length() - 1);
        query.deleteCharAt(query.length() - 1);

        String vnpSecureHash = hmacSHA512(secretKey, hashData.toString());
        String paymentUrl = endpoint + "?" + query.toString() + "&vnp_SecureHash=" + vnpSecureHash;

        // Kết quả trả về cho client
        Map<String, String> response = new HashMap<>();
        response.put("code", "100");
        response.put("message", "success");
        response.put("data", paymentUrl);

        return ResponseEntity.ok(response);
    }


    private String getRandomNumber(int length) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }

    private String getIpAddress() {
        return "127.0.0.1";
    }

    public int orderReturn(Map<String, String> requestData){
        String vnp_SecureHash = requestData.get("vnp_SecureHash");
        if (requestData.containsKey("vnp_SecureHashType")) {
            requestData.remove("vnp_SecureHashType");
        }
        if (requestData.containsKey("vnp_SecureHash")) {
            requestData.remove("vnp_SecureHash");
        }
        String signValue = hashAllFields(requestData);
        if (signValue.equals(vnp_SecureHash)) {
            if ("00".equals(requestData.get("vnp_TransactionStatus"))) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }
    public  String hashAllFields(Map fields) {
            List fieldNames = new ArrayList(fields.keySet());
        Collections.sort(fieldNames);
        StringBuilder sb = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) fields.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                sb.append(fieldName);
                sb.append("=");
                sb.append(fieldValue);
            }
            if (itr.hasNext()) {
                sb.append("&");
            }
        }
        return hmacSHA512(secretKey, sb.toString());
    }

    private String hmacSHA512(String key, String data) {
        try {
            // Khởi tạo đối tượng Mac với thuật toán HmacSHA512
            Mac sha512_HMAC = Mac.getInstance("HmacSHA512");

            // Tạo khóa từ chuỗi khóa
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
            sha512_HMAC.init(secret_key);

            // Tính toán mã băm
            byte[] hash = sha512_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8));

            // Chuyển đổi mảng byte thành chuỗi hex
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString(); // Trả về mã băm dưới dạng chuỗi hex
        } catch (Exception e) {
            throw new RuntimeException("Error while generating HMAC: " + e.getMessage(), e);
        }
    }
}
