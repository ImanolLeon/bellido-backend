package com.example.BelidoMap.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequestDto(
        @NotBlank @NotNull
        String dni,
        @NotBlank @NotNull
        String nombre,
        @NotBlank @NotNull
        String apellido,
        @NotBlank @NotNull
        String direccion,
        @NotBlank @NotNull
        String correo,
        @NotBlank @NotNull
        String sexo,
        @NotBlank @NotNull
        String fechaNacimiento,
        @NotNull
        long edad

) { }
