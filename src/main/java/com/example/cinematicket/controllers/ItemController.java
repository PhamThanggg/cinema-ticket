package com.example.cinematicket.controllers;

import com.example.cinematicket.dtos.requests.ItemRequest;
import com.example.cinematicket.dtos.responses.ApiResponse;
import com.example.cinematicket.dtos.responses.item.ItemResponse;
import com.example.cinematicket.services.item.ItemService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/item")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class ItemController {
    ItemService itemService;

    @PostMapping("")
    public ApiResponse<ItemResponse> create(
            @RequestBody @Valid ItemRequest request
    ){
        return ApiResponse.<ItemResponse>builder()
                .message("Create successfully")
                .result(itemService.createItem(request))
                .build();
    }

    @GetMapping("")
    public ApiResponse<List<ItemResponse>> getItemByCinemaId(
            @RequestParam("cinemaId") Long cinemaId
    ){
        return ApiResponse.<List<ItemResponse>>builder()
                .result(itemService.getItemByCinema(cinemaId))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ItemResponse> updateItemById(
            @PathVariable("id") Long id,
            @RequestBody @Valid ItemRequest request
    ){
        return ApiResponse.<ItemResponse>builder()
                .result(itemService.updateItem(request, id))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteItemById(@PathVariable("id") Long id){
        itemService.deleteItem(id);
        return ApiResponse.<String>builder()
                .result("Item has been deleted")
                .build();
    }
}
