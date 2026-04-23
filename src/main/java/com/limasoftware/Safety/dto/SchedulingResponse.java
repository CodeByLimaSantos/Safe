package com.limasoftware.Safety.dto;

import com.limasoftware.Safety.enums.SchedulingStatus;
import java.time.LocalDateTime;

public record SchedulingResponse(
        Long id,
        String name,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String client, //
        SchedulingStatus status,
        LocalDateTime createdAt,
        LocalDateTime attAt
) {
}