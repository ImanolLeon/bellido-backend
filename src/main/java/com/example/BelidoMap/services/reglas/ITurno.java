package com.example.BelidoMap.services.reglas;

import com.example.BelidoMap.dto.request.TurnoRequestDto;
import com.example.BelidoMap.dto.response.TurnoResponseDto;
import org.springframework.data.domain.Page;

public interface ITurno {
    TurnoResponseDto guardar(TurnoRequestDto turnoRequestDto);
    void eliminar(Long id);
    TurnoResponseDto actualizar(Long id, TurnoRequestDto turnoRequestDto);
    Page<TurnoResponseDto> listar(int pagina, int tamano);

}