package com.example.BelidoMap.controller;

import com.example.BelidoMap.dto.request.SalonAsignadoRequestDto;
import com.example.BelidoMap.dto.response.SalonAsignadoResponseDto;
import com.example.BelidoMap.services.implementacion.SalonAsignadoService;
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
@RequestMapping("/salon-asignado")
@AllArgsConstructor
public class SalonAsignadoController {
    private SalonAsignadoService salonAsignadoService;

    @PostMapping("/create")
    public ResponseEntity<SalonAsignadoResponseDto> crearSalonAsignado(@Valid @RequestBody SalonAsignadoRequestDto salonAsignadoRequestDto){
        SalonAsignadoResponseDto salonAsignadoResponseDto = salonAsignadoService.guardar(salonAsignadoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salonAsignadoResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<SalonAsignadoResponseDto>> listarSalonesAsignados(@RequestParam int pagina, @RequestParam int tamano){
        Page<SalonAsignadoResponseDto> salonesAsignados = salonAsignadoService.listar(pagina,tamano);
        return ResponseEntity.status(HttpStatus.OK).body(salonesAsignados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalonAsignadoResponseDto> actualizarSalonAsignado(@PathVariable Long id, @Valid @RequestBody SalonAsignadoRequestDto salonAsignadoRequestDto){
        SalonAsignadoResponseDto salonAsignadoResponseDto = salonAsignadoService.actualizar(id,salonAsignadoRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(salonAsignadoResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSalonAsignado(@PathVariable Long id){
        salonAsignadoService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}