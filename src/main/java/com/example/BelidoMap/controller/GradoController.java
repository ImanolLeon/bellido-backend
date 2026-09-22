package com.example.BelidoMap.controller;

import com.example.BelidoMap.dto.request.GradoRequestDto;
import com.example.BelidoMap.dto.response.GradoResponseDto;
import com.example.BelidoMap.services.implementacion.GradoService;
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
@RequestMapping("/grado")
@AllArgsConstructor
public class GradoController {
    private GradoService gradoService;

    @PostMapping("/create")
    public ResponseEntity<GradoResponseDto> crearGrado(@Valid @RequestBody GradoRequestDto gradoRequestDto){
        GradoResponseDto gradoResponseDto = gradoService.guardar(gradoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(gradoResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<GradoResponseDto>> listarGrados(@RequestParam int pagina, @RequestParam int tamano){
        Page<GradoResponseDto> grados = gradoService.listar(pagina,tamano);
        return ResponseEntity.status(HttpStatus.OK).body(grados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GradoResponseDto> actualizarGrado(@PathVariable Long id, @Valid @RequestBody GradoRequestDto gradoRequestDto){
        GradoResponseDto gradoResponseDto = gradoService.actualizar(id,gradoRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(gradoResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarGrado(@PathVariable Long id){
        gradoService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}