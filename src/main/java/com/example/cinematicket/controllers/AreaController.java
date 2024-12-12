package com.example.cinematicket.controllers;

import com.example.cinematicket.dtos.requests.AreaRequest;
import com.example.cinematicket.dtos.responses.ApiResponse;
import com.example.cinematicket.dtos.responses.AreaResponse;
import com.example.cinematicket.dtos.responses.GenreResponse;
import com.example.cinematicket.dtos.responses.PageResponse;
import com.example.cinematicket.services.area.AreaService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // đánh dấu là bean để spring quản lý
@RequestMapping("${api.prefix}/area") // định nghĩa đường dẫn
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true) // định nghĩa private final
@RequiredArgsConstructor // tạo constructor cho các trường là final
public class AreaController {
    AreaService areaService; // tiêm vào để sử dụng

    @PostMapping("") // thêm khu vực
    public ApiResponse<AreaResponse> create(
            @RequestBody @Valid AreaRequest request // dữ liệu nhận từ body + validate(@valid)
    ){
        return ApiResponse.<AreaResponse>builder() // sử dụng builder map dữ liệu
                .message("Create successfully")
                .result(areaService.createArea(request)) // gọi service tạo
                .build();
    }

    @GetMapping("") // lấy dữ liệu
    public ApiResponse<List<AreaResponse>> getAllArea(
    ){
        return ApiResponse.<List<AreaResponse>>builder()
                .result(areaService.getAreaALl()) // gọi service lấy dữ liệu
                .build();
    }

    @GetMapping("/search")
    public PageResponse<List<AreaResponse>> getAllGenre(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        Page<AreaResponse> responses = areaService
                .searchArea(name, page, limit);

        return PageResponse.<List<AreaResponse>>builder()
                .currentPage(responses.getNumber())
                .totalPages(responses.getTotalPages())
                .totalElements(responses.getTotalElements())
                .pageSize(responses.getSize())
                .result(responses.getContent())
                .build();
    }

    @PutMapping("/{id}") // sửa dữ liệu
    public ApiResponse<AreaResponse> updateAreaById(
            @PathVariable("id") Long id, // id nhận tên đường dẫn vd: area/1  id=1
            @RequestBody @Valid AreaRequest request // dữ liệu nhận từ body + validate(@valid)
    ){
        return ApiResponse.<AreaResponse>builder()
                .result(areaService.updateArea(request, id)) // gọi service sửa dữ liệu
                .build();
    }

    @DeleteMapping("/{id}") // xóa dữ liệu
    public ApiResponse<String> deleteAreaById(@PathVariable("id") Long id){
        areaService.deleteArea(id); // gọi service xóa dữ liệu
        return ApiResponse.<String>builder()
                .result("Area has been deleted")
                .build();
    }
}
