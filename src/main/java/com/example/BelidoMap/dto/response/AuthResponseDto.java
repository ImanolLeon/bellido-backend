package com.example.BelidoMap.dto.response;

import lombok.Builder;

@Builder
public record AuthResponseDto(
        String token,
        String dni,
        String nombre,
        String apellido,
        boolean admin,
        String estado,
        String mensaje
) {
}