package com.example.cinematicket.controllers;

import com.example.cinematicket.dtos.requests.PromotionRequest;
import com.example.cinematicket.dtos.responses.ApiResponse;
import com.example.cinematicket.dtos.responses.PageResponse;
import com.example.cinematicket.dtos.responses.PromotionResponse;
import com.example.cinematicket.services.promotion.PromotionService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
        return ApiResponse.<PromotionResponse>builder()
                .message("Create successfully")
                .result(promotionService.createPromotion(request))
                .build();
    }

    @GetMapping("")
    public PageResponse<List<PromotionResponse>> getAllPromotion(
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

    @DeleteMapping("/{id}")
    public ApiResponse<String> deletePromotionById(@PathVariable("id") String id){
        promotionService.deletePromotion(id);
        return ApiResponse.<String>builder()
                .result("Promotion has been deleted")
                .build();
    }
}
