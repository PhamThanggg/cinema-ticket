package com.example.cinematicket.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListTicketRequest {
        List<Long> scheduleID;

        List<Long> cinemaSeatId;

        List<Long> ticketTypeId;

        @JsonProperty("total_amount")
        Double TotalAmount;
}
