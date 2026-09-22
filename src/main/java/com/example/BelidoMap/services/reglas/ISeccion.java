package com.example.BelidoMap.services.reglas;

import com.example.BelidoMap.dto.request.SeccionRequestDto;
import com.example.BelidoMap.dto.response.SeccionResponseDto;
import org.springframework.data.domain.Page;

public interface ISeccion {
    SeccionResponseDto guardar(SeccionRequestDto seccionRequestDto);
    void eliminar(Long id);
    SeccionResponseDto actualizar(Long id, SeccionRequestDto seccionRequestDto);
    Page<SeccionResponseDto> listar(int pagina, int tamano);

}