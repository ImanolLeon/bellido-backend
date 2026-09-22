package com.example.BelidoMap.dto.response;

import lombok.Builder;

@Builder
public record ApoderadoActualizadoResponse (
    String nombre,
    String parentesco,
    String apellido,
    String dni

    ) {

}
