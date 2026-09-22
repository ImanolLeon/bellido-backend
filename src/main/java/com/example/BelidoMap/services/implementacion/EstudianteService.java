package com.example.BelidoMap.services.implementacion;

import com.example.BelidoMap.dto.request.EstudianteRequestDto;
import com.example.BelidoMap.dto.response.EstudianteResponseDto;
import com.example.BelidoMap.entities.Apoderado;
import com.example.BelidoMap.entities.Estudiante;
import com.example.BelidoMap.entities.SalonAsignado;
import com.example.BelidoMap.entities.Usuario;
import com.example.BelidoMap.exception.ApiException;
import com.example.BelidoMap.exception.MensajeException;
import com.example.BelidoMap.repository.ApoderadoRepositorio;
import com.example.BelidoMap.repository.EstudianteRepositorio;
import com.example.BelidoMap.repository.SalonAsignadoRepositorio;
import com.example.BelidoMap.repository.UsuarioRepositorio;
import com.example.BelidoMap.services.reglas.IEstudiante;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EstudianteService implements IEstudiante {

    private EstudianteRepositorio estudianteRepositorio;
    private UsuarioRepositorio usuarioRepositorio;
    private ApoderadoRepositorio apoderadoRepositorio;
    private SalonAsignadoRepositorio salonAsignadoRepositorio;


    @Transactional
    @Override
    public EstudianteResponseDto crear(EstudianteRequestDto estudianteRequestDto) {
        Usuario usuario = buscarUsuario(estudianteRequestDto.idUsuario());
        Apoderado apoderado = buscarApoderado(estudianteRequestDto.apoderadoId());

        Estudiante estudiante = new Estudiante();
        estudiante.setUsuario(usuario);
        estudiante.setApoderado(apoderado);

        estudianteRepositorio.save(estudiante);
        return construirResponse(estudiante,"Creado correctamente","Guardado correctamente");
    }

    @Override
    public void eliminar(Long estudianteId) {
        Estudiante estudiante = buscarId(estudianteId);
        estudianteRepositorio.deleteById(estudiante.getId());
    }

    @Transactional
    @Override
    public EstudianteResponseDto actualizar(Long estudianteId, EstudianteRequestDto estudianteRequestDto) {

        Estudiante estudiante = buscarId(estudianteId);
        Usuario usuario = buscarUsuario(estudianteRequestDto.idUsuario());
        Apoderado apoderado = buscarApoderado(estudianteRequestDto.apoderadoId());

        estudiante.setUsuario(usuario);
        estudiante.setApoderado(apoderado);

        estudianteRepositorio.save(estudiante);

        return construirResponse(estudiante,"Actualizado correctamente","Guardado correctamente");
    }

    @Override
    public Page<EstudianteResponseDto> listar(int pagina, int tamano) {
        Pageable pageable = PageRequest.of(pagina,tamano);
        Page<Estudiante> estudiantes = estudianteRepositorio.findAll(pageable);
        return estudiantes.map(estudiante -> construirResponse(estudiante,null,null));
    }

    @Transactional
    @Override
    public EstudianteResponseDto asignarSalonAsignado(Long estudianteId, Long salonAsignadoId) {
        Estudiante estudiante = buscarId(estudianteId);
        SalonAsignado salonAsignado = buscarSalonAsignado(salonAsignadoId);

        estudiante.setSalon(salonAsignado);
        estudianteRepositorio.save(estudiante);

        return construirResponse(estudiante,"Asignado correctamente","Se asignó el estudiante al salón asignado");
    }


    private Estudiante buscarId(Long id){
        return  estudianteRepositorio.findById(id).orElseThrow(
                () -> ApiException.userNotFound(MensajeException.VALOR_NO_ENCONTRADO.formatted("estudiante",id))
        );
    }

    private Usuario buscarUsuario(Long id){
        return  usuarioRepositorio.findById(id).orElseThrow(
                () -> ApiException.userNotFound(MensajeException.VALOR_NO_ENCONTRADO.formatted("usuario",id))
        );
    }

    private Apoderado buscarApoderado(Long id){
        return  apoderadoRepositorio.findById(id).orElseThrow(
                () -> ApiException.userNotFound(MensajeException.VALOR_NO_ENCONTRADO.formatted("apoderado",id))
        );
    }

    private SalonAsignado buscarSalonAsignado(Long id){
        return  salonAsignadoRepositorio.findById(id).orElseThrow(
                () -> ApiException.userNotFound(MensajeException.VALOR_NO_ENCONTRADO.formatted("salon asignado",id))
        );
    }

    private EstudianteResponseDto construirResponse(Estudiante estudiante, String estado, String mensaje){
        SalonAsignado salonAsignado = estudiante.getSalon();
        return EstudianteResponseDto.builder()
                .dni(estudiante.getUsuario().getDni())
                .nombre(estudiante.getUsuario().getNombre())
                .apellido(estudiante.getUsuario().getApellido())
                .salon(salonAsignado != null ? salonAsignado.getSalon().getNombre() : null)
                .seccion(salonAsignado != null ? salonAsignado.getSeccion().getNombre() : null)
                .grado(salonAsignado != null ? salonAsignado.getGrado().getNombre() : null)
                .turno(salonAsignado != null ? salonAsignado.getTurno().getNombre() : null)
                .estado(estado)
                .mensaje(mensaje)
                .build();
    }
}