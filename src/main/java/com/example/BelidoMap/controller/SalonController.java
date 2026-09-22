package com.example.BelidoMap.controller;

import com.example.BelidoMap.dto.request.SalonRequestDto;
import com.example.BelidoMap.dto.response.SalonResponseDto;
import com.example.BelidoMap.services.implementacion.SalonService;
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
@RequestMapping("/salon")
@AllArgsConstructor
public class SalonController {
    private SalonService salonService;

    @PostMapping("/create")
    public ResponseEntity<SalonResponseDto> crearSalon(@Valid @RequestBody SalonRequestDto salonRequestDto){
        SalonResponseDto salonResponseDto = salonService.guardar(salonRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salonResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<SalonResponseDto>> listarSalones(@RequestParam int pagina, @RequestParam int tamano){
        Page<SalonResponseDto> salones = salonService.listar(pagina,tamano);
        return ResponseEntity.status(HttpStatus.OK).body(salones);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalonResponseDto> actualizarSalon(@PathVariable Long id, @Valid @RequestBody SalonRequestDto salonRequestDto){
        SalonResponseDto salonResponseDto = salonService.actualizar(id,salonRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(salonResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSalon(@PathVariable Long id){
        salonService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}