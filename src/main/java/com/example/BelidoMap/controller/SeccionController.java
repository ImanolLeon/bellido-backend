package com.example.BelidoMap.controller;

import com.example.BelidoMap.dto.request.SeccionRequestDto;
import com.example.BelidoMap.dto.response.SeccionResponseDto;
import com.example.BelidoMap.services.implementacion.SeccionService;
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
@RequestMapping("/seccion")
@AllArgsConstructor
public class SeccionController {
    private SeccionService seccionService;

    @PostMapping("/create")
    public ResponseEntity<SeccionResponseDto> crearSeccion(@Valid @RequestBody SeccionRequestDto seccionRequestDto){
        SeccionResponseDto seccionResponseDto = seccionService.guardar(seccionRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(seccionResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<SeccionResponseDto>> listarSecciones(@RequestParam int pagina, @RequestParam int tamano){
        Page<SeccionResponseDto> secciones = seccionService.listar(pagina,tamano);
        return ResponseEntity.status(HttpStatus.OK).body(secciones);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeccionResponseDto> actualizarSeccion(@PathVariable Long id, @Valid @RequestBody SeccionRequestDto seccionRequestDto){
        SeccionResponseDto seccionResponseDto = seccionService.actualizar(id,seccionRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(seccionResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSeccion(@PathVariable Long id){
        seccionService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}