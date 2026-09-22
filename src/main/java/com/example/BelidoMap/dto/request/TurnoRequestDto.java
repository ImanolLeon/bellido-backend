package com.example.BelidoMap.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TurnoRequestDto(
        @NotBlank @NotNull
        String nombre,
        @NotBlank @NotNull
        String horario

) { }