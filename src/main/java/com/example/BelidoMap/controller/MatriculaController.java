package com.example.BelidoMap.controller;

import com.example.BelidoMap.dto.request.MatriculaRequestDto;
import com.example.BelidoMap.dto.response.MatriculaResponseDto;
import com.example.BelidoMap.entities.enums.EstadoMatricula;
import com.example.BelidoMap.services.implementacion.MatriculaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/matricula")
@AllArgsConstructor
public class MatriculaController {
    private MatriculaService matriculaService;

    @PostMapping("/create")
    public ResponseEntity<MatriculaResponseDto> crearMatricula(@Valid @RequestBody MatriculaRequestDto matriculaRequestDto){
        MatriculaResponseDto matriculaResponseDto = matriculaService.crear(matriculaRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(matriculaResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<MatriculaResponseDto>> listarMatriculas(@RequestParam int pagina, @RequestParam int tamano){
        Page<MatriculaResponseDto> matriculas = matriculaService.listar(pagina,tamano);
        return ResponseEntity.status(HttpStatus.OK).body(matriculas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculaResponseDto> actualizarMatricula(@PathVariable Long id, @Valid @RequestBody MatriculaRequestDto matriculaRequestDto){
        MatriculaResponseDto matriculaResponseDto = matriculaService.actualizar(id,matriculaRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(matriculaResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMatricula(@PathVariable Long id){
        matriculaService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}/estado/{estadoMatricula}")
    public ResponseEntity<MatriculaResponseDto> cambiarEstadoMatricula(@PathVariable Long id, @PathVariable EstadoMatricula estadoMatricula){
        MatriculaResponseDto matriculaResponseDto = matriculaService.cambiarEstado(id,estadoMatricula);
        return ResponseEntity.status(HttpStatus.OK).body(matriculaResponseDto);
    }

}