package com.example.BelidoMap.dto.response;

import lombok.Builder;

@Builder
public record EstudianteResponseDto(
    String dni,
    String nombre,
    String apellido,
    String salon,
    String seccion,
    String grado,
    String turno,
    String estado,
    String mensaje
) {
}