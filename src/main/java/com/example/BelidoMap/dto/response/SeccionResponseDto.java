package com.example.BelidoMap.dto.response;

import lombok.Builder;

@Builder
public record SeccionResponseDto(
    String nombre,
    String estado,
    String mensaje
) {
}