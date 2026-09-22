package com.example.BelidoMap.dto.request;

import jakarta.validation.constraints.NotNull;

public record EstudianteRequestDto(
        @NotNull
        Long idUsuario,
        @NotNull
        Long apoderadoId

) { }