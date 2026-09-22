package com.example.BelidoMap.dto.response;

import lombok.Builder;

@Builder
public record SalonAsignadoResponseDto(
    String salon,
    String seccion,
    String grado,
    String turno,
    String horario,
    String estado,
    String mensaje
) {
}