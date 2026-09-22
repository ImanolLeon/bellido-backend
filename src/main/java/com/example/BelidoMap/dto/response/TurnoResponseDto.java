package com.example.BelidoMap.dto.response;

import lombok.Builder;

@Builder
public record TurnoResponseDto(
    String nombre,
    String horario,
    String estado,
    String mensaje
) {
}