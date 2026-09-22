package com.example.BelidoMap.services.reglas;

import com.example.BelidoMap.dto.request.ApoderadoRequestDto;
import com.example.BelidoMap.dto.response.ApoderadoActualizadoResponse;
import com.example.BelidoMap.dto.response.ApoderadoResponseDto;
import org.springframework.data.domain.Page;

public interface IApoderado {
    ApoderadoResponseDto crearApoderado(ApoderadoRequestDto apoderadoRequestDto);
    Page<ApoderadoResponseDto> listarTodo(int pagina, int tamano);
    void eliminarApoderado(Long apoderadoId);
    void descartarEstudiante(Long estudianteId,Long apoderadoId);
    ApoderadoResponseDto agregarEstudiante(Long estudianteId,Long apoderadoId );
    ApoderadoActualizadoResponse actualizarApoderado(Long apoderadoId, ApoderadoRequestDto apoderadoRequestDto);

}