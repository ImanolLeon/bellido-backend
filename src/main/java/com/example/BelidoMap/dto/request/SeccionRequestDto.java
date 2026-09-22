package com.example.BelidoMap.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SeccionRequestDto(
        @NotBlank @NotNull
        String nombre

) { }