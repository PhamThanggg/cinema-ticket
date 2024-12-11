package com.example.cinematicket.controllers;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("${api.prefix}")
public class QRCodeController {
    @GetMapping("/qr-code")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_TICKET') or @securityService.isInvoiceOwner(#invoiceId, authentication)")
    public ResponseEntity<byte[]> generateQRCode(@RequestParam Long invoiceId,
                                                 @RequestParam String text,
                                                 @RequestParam(defaultValue = "200") int width,
                                                 @RequestParam(defaultValue = "200") int height) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            byte[] pngData = pngOutputStream.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "image/png");

            return ResponseEntity.ok().headers(headers).body(pngData);
        } catch (WriterException | IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
