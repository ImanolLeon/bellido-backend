package com.example.BelidoMap.dto.response;

import lombok.Builder;

@Builder
public record GradoResponseDto(
    String nombre,
    String estado,
    String mensaje
) {
}