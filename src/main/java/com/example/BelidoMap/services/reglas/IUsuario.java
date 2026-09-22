package com.example.BelidoMap.services.reglas;

import com.example.BelidoMap.dto.request.UsuarioRequestDto;
import com.example.BelidoMap.dto.response.UsuarioRespondeDto;
import org.springframework.data.domain.Page;

public interface IUsuario {
    UsuarioRespondeDto guardar(UsuarioRequestDto usuarioRequestDto);
    void eliminar(Long id);
    UsuarioRespondeDto actualizar(Long id, UsuarioRequestDto usuarioRequestDto);
    Page<UsuarioRespondeDto> listar(int pagina, int tamano);

}