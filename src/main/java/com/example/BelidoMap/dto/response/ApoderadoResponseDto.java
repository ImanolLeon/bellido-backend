package com.example.BelidoMap.dto.response;

import lombok.Builder;

@Builder
public record ApoderadoResponseDto (
        String dni,
        String nombre,
        String apellido,
        String parentesco,
        String mensaje
){
}