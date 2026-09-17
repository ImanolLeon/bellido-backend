package com.example.BelidoMap.services.implementacion;

import com.example.BelidoMap.dto.request.UsuarioRequestDto;
import com.example.BelidoMap.dto.response.UsuarioRespondeDto;
import com.example.BelidoMap.entities.Usuario;
import com.example.BelidoMap.repository.UsuarioRepositorio;
import com.example.BelidoMap.services.reglas.IUsuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService implements IUsuario {

    private UsuarioRepositorio usuarioRepositorio;


    @Override
    public UsuarioRespondeDto guardar(UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = Usuario.builder()
                .dni(usuarioRequestDto.dni())
                .nombre(usuarioRequestDto.nombre())
                .apellido(usuarioRequestDto.apellido())
                .correo(usuarioRequestDto.correo())
                .sexo(usuarioRequestDto.sexo())
                .fechaNacimiento(usuarioRequestDto.fechaNacimiento())
                .edad(usuarioRequestDto.edad())
                .build();

        usuarioRepositorio.save(usuario);
        return UsuarioRespondeDto.builder()
                .dni(usuario.getDni())
                .estado("Creado correctamente")
                .nombre(usuario.getNombre().concat(usuario.getApellido()))
                .build();
    }
}
