package com.limasoftware.Safety.dto;

import com.limasoftware.Safety.enums.SchedulingStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * DTO para atualização de agendamentos existentes.
 * Campos são opcionais na lógica de negócio, mas validados se enviados.
 */
public record SchedulingUpdateRequest(

        @Size(max = 120, message = "O nome não pode exceder 120 caracteres")
        String name,
        @Size(max = 4000)
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        @NotBlank
        String client
) {
}