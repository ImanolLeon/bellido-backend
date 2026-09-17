package com.example.BelidoMap.dto.request;

public record UsuarioRequestDto(
        String dni,
        String nombre,
        String apellido,
        String direccion,
        String correo,
        String sexo,
        String fechaNacimiento,
        long edad

) { }
