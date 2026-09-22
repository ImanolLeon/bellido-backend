package com.example.BelidoMap.services.implementacion;

import com.example.BelidoMap.dto.request.ApoderadoRequestDto;
import com.example.BelidoMap.dto.response.ApoderadoActualizadoResponse;
import com.example.BelidoMap.dto.response.ApoderadoResponseDto;
import com.example.BelidoMap.entities.Apoderado;
import com.example.BelidoMap.entities.Estudiante;
import com.example.BelidoMap.entities.Usuario;
import com.example.BelidoMap.repository.ApoderadoRepositorio;
import com.example.BelidoMap.repository.EstudianteRepositorio;
import com.example.BelidoMap.repository.UsuarioRepositorio;
import com.example.BelidoMap.services.reglas.IApoderado;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ApoderadoService implements IApoderado {

    private ApoderadoRepositorio apoderadoRepositorio;
    private UsuarioRepositorio usuarioRepositorio;
    private EstudianteRepositorio estudianteRepositorio;

    @Transactional
    @Override
    public ApoderadoResponseDto crearApoderado(ApoderadoRequestDto apoderadoRequestDto) {
        Usuario usuario = usuarioRepositorio.findById(apoderadoRequestDto.idUsuario()).
                orElseThrow(() -> new RuntimeException("No se ha encontrado al usuario con id " + apoderadoRequestDto.idUsuario() ));

        Apoderado apoderado = Apoderado.builder()
                .parentesco(apoderadoRequestDto.parentesco())
                .usuario(usuario)
                .build();
        apoderadoRepositorio.save(apoderado);
        return ApoderadoResponseDto.builder()
                .dni(apoderado.getUsuario().getDni())
                .nombre(apoderado.getUsuario().getNombre())
                .apellido(apoderado.getUsuario().getApellido())
                .parentesco(apoderado.getParentesco())
                .mensaje("Apoderado guardado con éxito")
                .build();
    }

    @Override
    public Page<ApoderadoResponseDto> listarTodo(int pagina, int tamano) {
        Pageable pageable = PageRequest.of(pagina,tamano);
        Page<Apoderado> apoderados = apoderadoRepositorio.findAll(pageable);
        return apoderados.map(apoderado -> ApoderadoResponseDto.builder()
                .dni(apoderado.getUsuario().getDni())
                .nombre(apoderado.getUsuario().getNombre())
                .apellido(apoderado.getUsuario().getApellido())
                .parentesco(apoderado.getParentesco())
                .build());
    }

    @Override
    public void eliminarApoderado(Long apoderadoId) {
        Apoderado  apoderado = apoderadoRepositorio.findById(apoderadoId).
                orElseThrow(() -> new RuntimeException("No se encontró el id"));
        usuarioRepositorio.deleteById(apoderadoId);
    }

    @Override
    public void descartarEstudiante(Long estudianteId, Long apoderadoId) {
        Apoderado apoderado = apoderadoRepositorio.findById(apoderadoId).
                orElseThrow(() -> new RuntimeException("No se encontró"));
        Estudiante estudiante = estudianteRepositorio.findById(estudianteId).
                orElseThrow(() -> new RuntimeException("No se encontró"));
        apoderadoRepositorio.deleteByEstudianteId(estudianteId);
    }

    @Transactional
    @Override
    public ApoderadoResponseDto agregarEstudiante(Long estudianteId, Long apoderadoId) {
        Estudiante estudiante = estudianteRepositorio.findById(estudianteId).
                orElseThrow(() -> new RuntimeException("No se encontró"));
        Apoderado apoderado= buscarPorIdApoderado(apoderadoId);
        List<Estudiante> listaEstudiantes = apoderado.getEstudiante();

        if(listaEstudiantes == null){
            listaEstudiantes = new ArrayList<>();
        }
        listaEstudiantes.add(estudiante);
        estudiante.setApoderado(apoderado);
        estudianteRepositorio.save(estudiante);

        apoderado.setEstudiante(listaEstudiantes);
        apoderadoRepositorio.save(apoderado);
        return ApoderadoResponseDto.builder()
                .dni(apoderado.getUsuario().getDni())
                .nombre(apoderado.getUsuario().getNombre())
                .apellido(apoderado.getUsuario().getApellido())
                .parentesco(apoderado.getParentesco())
                .mensaje("Se agregó correctamente el estudiante " +
                        estudiante.getUsuario().getNombre()
                        +  estudiante.getUsuario().getApellido())
                .build();
    }

    @Transactional
    @Override
    public ApoderadoActualizadoResponse actualizarApoderado(Long apoderadoId, ApoderadoRequestDto apoderadoRequestDto) {
        Apoderado apoderado = buscarPorIdApoderado(apoderadoId);
        apoderado.setParentesco(apoderadoRequestDto.parentesco());
        apoderadoRepositorio.save(apoderado);
        return ApoderadoActualizadoResponse.builder()
                .nombre(apoderado.getUsuario().getNombre())
                .dni(apoderado.getUsuario().getDni())
                .parentesco(apoderado.getParentesco())
                .apellido(apoderado.getUsuario().getApellido())
                .build();
    }

    private Apoderado buscarPorIdApoderado (Long apoderadoId){
        return apoderadoRepositorio.findById(apoderadoId).orElseThrow(
                () -> new RuntimeException("No se encontró")
        );
    }

}
