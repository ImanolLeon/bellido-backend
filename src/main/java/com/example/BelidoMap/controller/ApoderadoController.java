package com.example.BelidoMap.controller;

import com.example.BelidoMap.dto.request.ApoderadoRequestDto;
import com.example.BelidoMap.dto.response.ApoderadoActualizadoResponse;
import com.example.BelidoMap.dto.response.ApoderadoResponseDto;
import com.example.BelidoMap.services.implementacion.ApoderadoService;
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
@RequestMapping("/apoderado")
@AllArgsConstructor
public class ApoderadoController {
    private ApoderadoService apoderadoService;

    @PostMapping("/create")
    public ResponseEntity<ApoderadoResponseDto> crearApoderado(@RequestBody ApoderadoRequestDto apoderadoRequestDto){
        ApoderadoResponseDto apoderadoResponseDto = apoderadoService.crearApoderado(apoderadoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(apoderadoResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<ApoderadoResponseDto>> listarApoderados(@RequestParam int pagina, @RequestParam int tamano){
        Page<ApoderadoResponseDto> apoderados = apoderadoService.listarTodo(pagina,tamano);
        return ResponseEntity.status(HttpStatus.OK).body(apoderados);
    }

    @PutMapping("/{apoderadoId}")
    public ResponseEntity<ApoderadoActualizadoResponse> actualizarApoderado(@PathVariable Long apoderadoId, @RequestBody ApoderadoRequestDto apoderadoRequestDto){
        ApoderadoActualizadoResponse apoderadoActualizadoResponse = apoderadoService.actualizarApoderado(apoderadoId,apoderadoRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(apoderadoActualizadoResponse);
    }

    @DeleteMapping("/{apoderadoId}")
    public ResponseEntity<Void> eliminarApoderado(@PathVariable Long apoderadoId){
        apoderadoService.eliminarApoderado(apoderadoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{apoderadoId}/estudiante/{estudianteId}")
    public ResponseEntity<ApoderadoResponseDto> agregarEstudiante(@PathVariable Long apoderadoId, @PathVariable Long estudianteId){
        ApoderadoResponseDto apoderadoResponseDto = apoderadoService.agregarEstudiante(estudianteId,apoderadoId);
        return ResponseEntity.status(HttpStatus.OK).body(apoderadoResponseDto);
    }

    @DeleteMapping("/{apoderadoId}/estudiante/{estudianteId}")
    public ResponseEntity<Void> descartarEstudiante(@PathVariable Long apoderadoId, @PathVariable Long estudianteId){
        apoderadoService.descartarEstudiante(estudianteId,apoderadoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}