package com.limasoftware.Safety.mapper;

import com.limasoftware.Safety.dto.SchedulingCreateRequest;
import com.limasoftware.Safety.dto.SchedulingResponse;
import com.limasoftware.Safety.dto.SchedulingUpdateRequest;
import com.limasoftware.Safety.enums.SchedulingStatus;
import com.limasoftware.Safety.model.Client;
import com.limasoftware.Safety.model.Scheduling;

import java.time.LocalDateTime;



public class SchedulingMapper {

    public static Scheduling toEntity(SchedulingCreateRequest request) {
        return Scheduling.builder()
                .name(request.name())
                .description(request.description())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .client(request.client())
                .status(SchedulingStatus.SCHEDULED)
                .createdAt(LocalDateTime.now())
                .attAt(LocalDateTime.now())

                .build();


    }

    public static void merge(Scheduling entity, SchedulingUpdateRequest request) {
        if (request.name() != null ) {
            entity.setName(request.name());

        }
        if (request.description() != null) {
            entity.setDescription(request.description());

        }
        if (request.startDate() != null ) {
            entity.setStartDate(request.startDate());

        }
        if (request.endDate() != null ) {
            entity.setEndDate(request.endDate());

        }
    }



    public static SchedulingResponse toResponse(Scheduling scheduling) {
        return new SchedulingResponse(

                scheduling.getId(),
                scheduling.getName(),
                scheduling.getDescription(),
                scheduling.getStartDate(),
                scheduling.getEndDate(),
                scheduling.getClient(),
                scheduling.getStatus(),
                scheduling.getCreatedAt(),
                scheduling.getAttAt()

        );


    }
}
