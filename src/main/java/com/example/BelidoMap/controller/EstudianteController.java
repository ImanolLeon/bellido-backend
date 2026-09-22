package com.example.BelidoMap.controller;

import com.example.BelidoMap.dto.request.EstudianteRequestDto;
import com.example.BelidoMap.dto.response.EstudianteResponseDto;
import com.example.BelidoMap.services.implementacion.EstudianteService;
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

@RestController
@RequestMapping("/estudiante")
@AllArgsConstructor
public class EstudianteController {
    private EstudianteService estudianteService;

    @PostMapping("/create")
    public ResponseEntity<EstudianteResponseDto> crearEstudiante(@Valid @RequestBody EstudianteRequestDto estudianteRequestDto){
        EstudianteResponseDto estudianteResponseDto = estudianteService.crear(estudianteRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<EstudianteResponseDto>> listarEstudiantes(@RequestParam int pagina, @RequestParam int tamano){
        Page<EstudianteResponseDto> estudiantes = estudianteService.listar(pagina,tamano);
        return ResponseEntity.status(HttpStatus.OK).body(estudiantes);
    }

    @PutMapping("/{estudianteId}")
    public ResponseEntity<EstudianteResponseDto> actualizarEstudiante(@PathVariable Long estudianteId, @Valid @RequestBody EstudianteRequestDto estudianteRequestDto){
        EstudianteResponseDto estudianteResponseDto = estudianteService.actualizar(estudianteId,estudianteRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(estudianteResponseDto);
    }

    @DeleteMapping("/{estudianteId}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long estudianteId){
        estudianteService.eliminar(estudianteId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{estudianteId}/salon-asignado/{salonAsignadoId}")
    public ResponseEntity<EstudianteResponseDto> asignarSalonAsignado(@PathVariable Long estudianteId, @PathVariable Long salonAsignadoId){
        EstudianteResponseDto estudianteResponseDto = estudianteService.asignarSalonAsignado(estudianteId,salonAsignadoId);
        return ResponseEntity.status(HttpStatus.OK).body(estudianteResponseDto);
    }

}