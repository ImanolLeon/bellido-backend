package com.example.BelidoMap.services.reglas;

import com.example.BelidoMap.dto.request.SalonAsignadoRequestDto;
import com.example.BelidoMap.dto.response.SalonAsignadoResponseDto;
import org.springframework.data.domain.Page;

public interface ISalonAsignado {
    SalonAsignadoResponseDto guardar(SalonAsignadoRequestDto salonAsignadoRequestDto);
    void eliminar(Long id);
    SalonAsignadoResponseDto actualizar(Long id, SalonAsignadoRequestDto salonAsignadoRequestDto);
    Page<SalonAsignadoResponseDto> listar(int pagina, int tamano);

}