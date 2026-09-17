package com.example.BelidoMap.services.reglas;

import com.example.BelidoMap.dto.request.UsuarioRequestDto;
import com.example.BelidoMap.dto.response.UsuarioRespondeDto;
import com.example.BelidoMap.repository.UsuarioRepositorio;

public interface IUsuario {
    UsuarioRespondeDto guardar(UsuarioRequestDto usuarioRequestDto);



}
