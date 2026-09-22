package com.example.BelidoMap.services.reglas;

import com.example.BelidoMap.dto.request.EstudianteRequestDto;
import com.example.BelidoMap.dto.response.EstudianteResponseDto;
import org.springframework.data.domain.Page;

public interface IEstudiante {
    EstudianteResponseDto crear(EstudianteRequestDto estudianteRequestDto);
    void eliminar(Long estudianteId);
    EstudianteResponseDto actualizar(Long estudianteId, EstudianteRequestDto estudianteRequestDto);
    Page<EstudianteResponseDto> listar(int pagina, int tamano);
    EstudianteResponseDto asignarSalonAsignado(Long estudianteId, Long salonAsignadoId);

}