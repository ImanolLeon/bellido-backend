package com.example.BelidoMap.services.implementacion;

import com.example.BelidoMap.dto.request.UsuarioRequestDto;
import com.example.BelidoMap.dto.response.UsuarioRespondeDto;
import com.example.BelidoMap.entities.Usuario;
import com.example.BelidoMap.exception.ApiException;
import com.example.BelidoMap.exception.MensajeException;
import com.example.BelidoMap.repository.UsuarioRepositorio;
import com.example.BelidoMap.services.reglas.IUsuario;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class UsuarioService implements IUsuario {

    private UsuarioRepositorio usuarioRepositorio;


    @Override
    public UsuarioRespondeDto guardar(UsuarioRequestDto usuarioRequestDto) {
        validarDniDisponible(usuarioRequestDto.dni());

        Usuario usuario = Usuario.builder()
                .dni(usuarioRequestDto.dni())
                .nombre(usuarioRequestDto.nombre())
                .apellido(usuarioRequestDto.apellido())
                .correo(usuarioRequestDto.correo())
                .sexo(usuarioRequestDto.sexo())
                .direccion(usuarioRequestDto.direccion())
                .fechaNacimiento(usuarioRequestDto.fechaNacimiento())
                .edad(usuarioRequestDto.edad())
                .fechaRegistro(LocalDate.now())
                .build();

        usuarioRepositorio.save(usuario);
        return UsuarioRespondeDto.builder()
                .dni(usuario.getDni())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .correo(usuario.getCorreo())
                .estado("Creado correctamente")
                .mensaje("Guardado correctamente")
                .build();
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = buscarId(id);
        usuarioRepositorio.deleteById(usuario.getId());
    }

    @Transactional
    @Override
    public UsuarioRespondeDto actualizar(Long id, UsuarioRequestDto usuarioRequestDto) {

        Usuario usuario = buscarId(id);
        usuario.setDni(usuarioRequestDto.dni());
        usuario.setNombre(usuarioRequestDto.nombre());
        usuario.setApellido(usuarioRequestDto.apellido());
        usuario.setDireccion(usuarioRequestDto.direccion());
        usuario.setCorreo(usuarioRequestDto.correo());
        usuario.setSexo(usuarioRequestDto.sexo());
        usuario.setFechaNacimiento(usuarioRequestDto.fechaNacimiento());
        usuario.setEdad(usuarioRequestDto.edad());

        usuarioRepositorio.save(usuario);

        return UsuarioRespondeDto.builder()
                .dni(usuario.getDni())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .correo(usuario.getCorreo())
                .estado("Actualizado correctamente")
                .mensaje("Guardado correctamente")
                .build();
    }

    @Override
    public Page<UsuarioRespondeDto> listar(int pagina, int tamano) {
        Pageable pageable = PageRequest.of(pagina,tamano);
        Page<Usuario> usuarios = usuarioRepositorio.findAll(pageable);
        return usuarios.map(usuario -> UsuarioRespondeDto.builder()
                .dni(usuario.getDni())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .correo(usuario.getCorreo())
                .build());
    }


    private Usuario buscarId(Long id){
        return  usuarioRepositorio.findById(id).orElseThrow(
                () -> ApiException.userNotFound(MensajeException.VALOR_NO_ENCONTRADO.formatted("usuario",id))
        );
    }

    private void validarDniDisponible(String dni){
        if(usuarioRepositorio.existsByDni(dni)){
            throw ApiException.valorYaEnUso(MensajeException.VALOR_YA_EN_USO.formatted("usuario","dni",dni));
        }
    }
}
