package com.example.BelidoMap.dto.response;

import com.example.BelidoMap.entities.enums.EstadoMatricula;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MatriculaResponseDto(
        String dni,
        String nombre,
        String apellido,
        String salon,
        String seccion,
        String grado,
        String turno,
        LocalDate fecha,
        String metodoPago,
        EstadoMatricula estadoMatricula,
        String estado,
        String mensaje
) {
}