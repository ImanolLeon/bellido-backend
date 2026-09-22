package com.example.BelidoMap.controller;

import com.example.BelidoMap.dto.request.TurnoRequestDto;
import com.example.BelidoMap.dto.response.TurnoResponseDto;
import com.example.BelidoMap.services.implementacion.TurnoService;
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
@RequestMapping("/turno")
@AllArgsConstructor
public class TurnoController {
    private TurnoService turnoService;

    @PostMapping("/create")
    public ResponseEntity<TurnoResponseDto> crearTurno(@Valid @RequestBody TurnoRequestDto turnoRequestDto){
        TurnoResponseDto turnoResponseDto = turnoService.guardar(turnoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(turnoResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<TurnoResponseDto>> listarTurnos(@RequestParam int pagina, @RequestParam int tamano){
        Page<TurnoResponseDto> turnos = turnoService.listar(pagina,tamano);
        return ResponseEntity.status(HttpStatus.OK).body(turnos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurnoResponseDto> actualizarTurno(@PathVariable Long id, @Valid @RequestBody TurnoRequestDto turnoRequestDto){
        TurnoResponseDto turnoResponseDto = turnoService.actualizar(id,turnoRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(turnoResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTurno(@PathVariable Long id){
        turnoService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}