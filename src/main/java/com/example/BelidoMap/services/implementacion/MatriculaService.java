package com.example.BelidoMap.services.implementacion;

import com.example.BelidoMap.dto.request.MatriculaRequestDto;
import com.example.BelidoMap.dto.response.MatriculaResponseDto;
import com.example.BelidoMap.entities.Estudiante;
import com.example.BelidoMap.entities.Matricula;
import com.example.BelidoMap.entities.SalonAsignado;
import com.example.BelidoMap.entities.enums.EstadoMatricula;
import com.example.BelidoMap.exception.ApiException;
import com.example.BelidoMap.exception.MensajeException;
import com.example.BelidoMap.repository.EstudianteRepositorio;
import com.example.BelidoMap.repository.MatriculaRepositorio;
import com.example.BelidoMap.services.reglas.IMatricula;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class MatriculaService implements IMatricula {

    private MatriculaRepositorio matriculaRepositorio;
    private EstudianteRepositorio estudianteRepositorio;

    @Transactional
    @Override
    public MatriculaResponseDto crear(MatriculaRequestDto matriculaRequestDto) {
        Estudiante estudiante = buscarEstudiante(matriculaRequestDto.idEstudiante());

        Matricula matricula = new Matricula();
        matricula.setEstudiante(estudiante);
        matricula.setFecha(matriculaRequestDto.fecha() != null ? matriculaRequestDto.fecha() : LocalDate.now());
        matricula.setMetodoPago(matriculaRequestDto.metodoPago());
        matricula.setEstadoMatricula(matriculaRequestDto.estadoMatricula() != null
                ? matriculaRequestDto.estadoMatricula() : EstadoMatricula.PENDIENTE);

        matriculaRepositorio.save(matricula);
        return construirResponse(matricula,"Creado correctamente","Guardado correctamente");
    }

    @Override
    public void eliminar(Long id) {
        Matricula matricula = buscarId(id);
        matriculaRepositorio.deleteById(matricula.getId());
    }

    @Transactional
    @Override
    public MatriculaResponseDto actualizar(Long id, MatriculaRequestDto matriculaRequestDto) {

        Matricula matricula = buscarId(id);
        Estudiante estudiante = buscarEstudiante(matriculaRequestDto.idEstudiante());

        matricula.setEstudiante(estudiante);
        matricula.setFecha(matriculaRequestDto.fecha() != null ? matriculaRequestDto.fecha() : matricula.getFecha());
        matricula.setMetodoPago(matriculaRequestDto.metodoPago());
        matricula.setEstadoMatricula(matriculaRequestDto.estadoMatricula() != null
                ? matriculaRequestDto.estadoMatricula() : matricula.getEstadoMatricula());

        matriculaRepositorio.save(matricula);

        return construirResponse(matricula,"Actualizado correctamente","Guardado correctamente");
    }

    @Override
    public Page<MatriculaResponseDto> listar(int pagina, int tamano) {
        Pageable pageable = PageRequest.of(pagina,tamano);
        Page<Matricula> matriculas = matriculaRepositorio.findAll(pageable);
        return matriculas.map(matricula -> construirResponse(matricula,null,null));
    }

    @Transactional
    @Override
    public MatriculaResponseDto cambiarEstado(Long id, EstadoMatricula estadoMatricula) {
        Matricula matricula = buscarId(id);
        matricula.setEstadoMatricula(estadoMatricula);
        matriculaRepositorio.save(matricula);

        return construirResponse(matricula,"Estado actualizado correctamente",
                "Se actualizó el estado a " + estadoMatricula);
    }

    private Matricula buscarId(Long id){
        return  matriculaRepositorio.findById(id).orElseThrow(
                () -> ApiException.userNotFound(MensajeException.VALOR_NO_ENCONTRADO.formatted("matricula",id))
        );
    }

    private Estudiante buscarEstudiante(Long id){
        return  estudianteRepositorio.findById(id).orElseThrow(
                () -> ApiException.userNotFound(MensajeException.VALOR_NO_ENCONTRADO.formatted("estudiante",id))
        );
    }

    private MatriculaResponseDto construirResponse(Matricula matricula, String estado, String mensaje){
        Estudiante estudiante = matricula.getEstudiante();
        SalonAsignado salonAsignado = estudiante != null ? estudiante.getSalon() : null;
        return MatriculaResponseDto.builder()
                .dni(estudiante != null ? estudiante.getUsuario().getDni() : null)
                .nombre(estudiante != null ? estudiante.getUsuario().getNombre() : null)
                .apellido(estudiante != null ? estudiante.getUsuario().getApellido() : null)
                .salon(salonAsignado != null ? salonAsignado.getSalon().getNombre() : null)
                .seccion(salonAsignado != null ? salonAsignado.getSeccion().getNombre() : null)
                .grado(salonAsignado != null ? salonAsignado.getGrado().getNombre() : null)
                .turno(salonAsignado != null ? salonAsignado.getTurno().getNombre() : null)
                .fecha(matricula.getFecha())
                .metodoPago(matricula.getMetodoPago())
                .estadoMatricula(matricula.getEstadoMatricula())
                .estado(estado)
                .mensaje(mensaje)
                .build();
    }
}