package com.example.BelidoMap.dto.response;

import lombok.Builder;

@Builder
public record UsuarioRespondeDto(
    String dni,
    String nombre,
    String estado
) {
}
