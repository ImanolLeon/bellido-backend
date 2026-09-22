package com.example.BelidoMap.dto.request;

import com.example.BelidoMap.entities.enums.EstadoMatricula;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MatriculaRequestDto(
        @NotNull
        Long idEstudiante,
        LocalDate fecha,
        @NotBlank @NotNull
        String metodoPago,
        EstadoMatricula estadoMatricula

) { }