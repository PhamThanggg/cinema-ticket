package com.example.cinematicket.controllers;

import com.example.cinematicket.dtos.requests.promotion.PromotionInfoRequest;
import com.example.cinematicket.dtos.requests.promotion.PromotionRequest;
import com.example.cinematicket.dtos.responses.ApiResponse;
import com.example.cinematicket.dtos.responses.MovieImageResponse;
import com.example.cinematicket.dtos.responses.PageResponse;
import com.example.cinematicket.dtos.responses.PromotionResponse;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.services.promotion.PromotionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/promotion")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class PromotionController {
    PromotionService promotionService;

    @PostMapping("")
    public ApiResponse<PromotionResponse> create(
            @RequestBody @Valid PromotionRequest request
    ){
        if(request.getDiscountType().equals("PHAM_TRAM")){
            if(request.getMin() <= 0 || request.getMax() > 100){
                throw new AppException(ErrorCode.DISCOUNT_P);
            }
        }
        return ApiResponse.<PromotionResponse>builder()
                .message("Create successfully")
                .result(promotionService.createPromotion(request))
                .build();
    }

    @PostMapping(value = "upload_images/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<PromotionResponse> uploadImages(
            @PathVariable("id") String promotionId,
            @ModelAttribute List<MultipartFile> files
    ) throws IOException {
        return ApiResponse.<PromotionResponse>builder()
                .message("Thêm ảnh thành công")
                .result(promotionService.createPromotionImage(promotionId, files))
                .build();
    }

    @PostMapping("/info")
    public ApiResponse<PromotionResponse> createInfo(
            @RequestBody @Valid PromotionInfoRequest request
    ){
        return ApiResponse.<PromotionResponse>builder()
                .message("Create successfully")
                .result(promotionService.createPromotionInfo(request))
                .build();
    }

    @GetMapping("")
    public PageResponse<List<PromotionResponse>> getAllPromotion(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "promotionType", required = false) String promotionType,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        Page<PromotionResponse> responses = promotionService.getPromotionALl(name, promotionType, status, page, limit);
        return PageResponse.<List<PromotionResponse>>builder()
                .currentPage(responses.getNumber())
                .totalPages(responses.getTotalPages())
                .totalElements(responses.getTotalElements())
                .pageSize(responses.getSize())
                .result(responses.getContent())
                .build();
    }

    @GetMapping("/info")
    public PageResponse<List<PromotionResponse>> getAllPromotionInfo(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        Page<PromotionResponse> responses = promotionService.getPromotionALl(name, page, limit);
        return PageResponse.<List<PromotionResponse>>builder()
                .currentPage(responses.getNumber())
                .totalPages(responses.getTotalPages())
                .totalElements(responses.getTotalElements())
                .pageSize(responses.getSize())
                .result(responses.getContent())
                .build();
    }

    @GetMapping("/{name}")
    public ApiResponse<PromotionResponse> getPromotionByName(
            @PathVariable("name") String name
    ){
        return ApiResponse.<PromotionResponse>builder()
                .result(promotionService.getPromotionByName(name))
                .build();
    }

    @GetMapping("get/{id}")
    public ApiResponse<PromotionResponse> getPromotionById(
            @PathVariable("id") String name
    ){
        return ApiResponse.<PromotionResponse>builder()
                .result(promotionService.getPromotionById(name))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<PromotionResponse> updatePromotionById(
            @PathVariable("id") String id,
            @RequestBody @Valid PromotionRequest request
    ){
        return ApiResponse.<PromotionResponse>builder()
                .result(promotionService.updatePromotion(request, id))
                .build();
    }

    @PutMapping("info/{id}")
    public ApiResponse<PromotionResponse> updatePromotionInfoById(
            @PathVariable("id") String id,
            @RequestBody @Valid PromotionInfoRequest request
    ){
        return ApiResponse.<PromotionResponse>builder()
                .result(promotionService.updatePromotionInfo(request, id))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deletePromotionById(@PathVariable("id") String id){
        promotionService.deletePromotion(id);
        return ApiResponse.<String>builder()
                .result("Promotion has been deleted")
                .build();
    }
}
