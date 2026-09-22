package com.example.BelidoMap.dto.response;

import lombok.Builder;

@Builder
public record SalonResponseDto(
    String nombre,
    long cantidadAlumnos,
    String estado,
    String mensaje
) {
}