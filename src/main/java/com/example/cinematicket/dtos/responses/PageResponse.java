package com.example.cinematicket.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResponse<T> {
    @Builder.Default
    int code = 1000;

    String message;

    int currentPage;

    int totalPages;

    long totalElements;

    int pageSize;

    T result;
}
