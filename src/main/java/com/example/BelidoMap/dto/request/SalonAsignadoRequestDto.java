package com.example.BelidoMap.dto.request;

import jakarta.validation.constraints.NotNull;

public record SalonAsignadoRequestDto(
        @NotNull
        Long salonId,
        @NotNull
        Long seccionId,
        @NotNull
        Long gradoId,
        @NotNull
        Long turnoId

) { }