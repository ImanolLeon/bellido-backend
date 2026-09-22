package com.example.BelidoMap.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ApoderadoRequestDto (
        @NotNull
        String parentesco,

        Long idUsuario

){
}
