package com.example.BelidoMap.services.reglas;

import com.example.BelidoMap.dto.request.MatriculaRequestDto;
import com.example.BelidoMap.dto.response.MatriculaResponseDto;
import com.example.BelidoMap.entities.enums.EstadoMatricula;
import org.springframework.data.domain.Page;

public interface IMatricula {
    MatriculaResponseDto crear(MatriculaRequestDto matriculaRequestDto);
    void eliminar(Long id);
    MatriculaResponseDto actualizar(Long id, MatriculaRequestDto matriculaRequestDto);
    Page<MatriculaResponseDto> listar(int pagina, int tamano);
    MatriculaResponseDto cambiarEstado(Long id, EstadoMatricula estadoMatricula);

}