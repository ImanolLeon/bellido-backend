package com.example.BelidoMap.controller;

import com.example.BelidoMap.dto.request.UsuarioRequestDto;
import com.example.BelidoMap.dto.response.UsuarioRespondeDto;
import com.example.BelidoMap.services.implementacion.UsuarioService;
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
@RequestMapping("/usuario")
@AllArgsConstructor
public class UsuarioController {
    private UsuarioService usuarioService;

    @PostMapping("/create")
    public ResponseEntity<UsuarioRespondeDto> crearUsuario(@Valid @RequestBody UsuarioRequestDto usuarioRequestDto){
        UsuarioRespondeDto usuarioRespondeDto = usuarioService.guardar(usuarioRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRespondeDto);
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioRespondeDto>> listarUsuarios(@RequestParam int pagina, @RequestParam int tamano){
        Page<UsuarioRespondeDto> usuarios = usuarioService.listar(pagina,tamano);
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioRespondeDto> actualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDto usuarioRequestDto){
        UsuarioRespondeDto usuarioRespondeDto = usuarioService.actualizar(id,usuarioRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRespondeDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id){
        usuarioService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}