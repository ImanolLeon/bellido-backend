package com.example.BelidoMap.services.reglas;

import com.example.BelidoMap.dto.request.GradoRequestDto;
import com.example.BelidoMap.dto.response.GradoResponseDto;
import org.springframework.data.domain.Page;

public interface IGrado {
    GradoResponseDto guardar(GradoRequestDto gradoRequestDto);
    void eliminar(Long id);
    GradoResponseDto actualizar(Long id, GradoRequestDto gradoRequestDto);
    Page<GradoResponseDto> listar(int pagina, int tamano);

}