package com.example.BelidoMap.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SalonRequestDto(
        @NotBlank @NotNull
        String nombre,
        @NotNull
        long cantidadAlumnos

) { }