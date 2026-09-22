package com.example.BelidoMap.services.reglas;

import com.example.BelidoMap.dto.request.SalonRequestDto;
import com.example.BelidoMap.dto.response.SalonResponseDto;
import org.springframework.data.domain.Page;

public interface ISalon {
    SalonResponseDto guardar(SalonRequestDto salonRequestDto);
    void eliminar(Long id);
    SalonResponseDto actualizar(Long id, SalonRequestDto salonRequestDto);
    Page<SalonResponseDto> listar(int pagina, int tamano);

}