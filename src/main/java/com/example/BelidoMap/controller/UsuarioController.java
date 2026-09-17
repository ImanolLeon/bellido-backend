package com.example.BelidoMap.controller;

import com.example.BelidoMap.dto.request.UsuarioRequestDto;
import com.example.BelidoMap.dto.response.UsuarioRespondeDto;
import com.example.BelidoMap.services.implementacion.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@AllArgsConstructor
public class UsuarioController {
    private UsuarioService usuarioService;

    @PostMapping("/create")
    public ResponseEntity<UsuarioRespondeDto> registarUsuario(@RequestBody UsuarioRequestDto usuarioRequestDto){
        UsuarioRespondeDto usuarioRespondeDto= usuarioService.guardar(usuarioRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRespondeDto);
    }

}
