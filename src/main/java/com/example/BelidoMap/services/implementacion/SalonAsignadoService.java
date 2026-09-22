package com.example.BelidoMap.services.implementacion;

import com.example.BelidoMap.dto.request.SalonAsignadoRequestDto;
import com.example.BelidoMap.dto.response.SalonAsignadoResponseDto;
import com.example.BelidoMap.entities.Grado;
import com.example.BelidoMap.entities.Salon;
import com.example.BelidoMap.entities.SalonAsignado;
import com.example.BelidoMap.entities.Seccion;
import com.example.BelidoMap.entities.Turno;
import com.example.BelidoMap.exception.ApiException;
import com.example.BelidoMap.exception.MensajeException;
import com.example.BelidoMap.repository.GradoRepositorio;
import com.example.BelidoMap.repository.SalonAsignadoRepositorio;
import com.example.BelidoMap.repository.SalonRepositorio;
import com.example.BelidoMap.repository.SeccionRepositorio;
import com.example.BelidoMap.repository.TurnoRepositorio;
import com.example.BelidoMap.services.reglas.ISalonAsignado;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SalonAsignadoService implements ISalonAsignado {

    private SalonAsignadoRepositorio salonAsignadoRepositorio;
    private SalonRepositorio salonRepositorio;
    private SeccionRepositorio seccionRepositorio;
    private GradoRepositorio gradoRepositorio;
    private TurnoRepositorio turnoRepositorio;


    @Override
    public SalonAsignadoResponseDto guardar(SalonAsignadoRequestDto salonAsignadoRequestDto) {
        Salon salon = buscarSalon(salonAsignadoRequestDto.salonId());
        Seccion seccion = buscarSeccion(salonAsignadoRequestDto.seccionId());
        Grado grado = buscarGrado(salonAsignadoRequestDto.gradoId());
        Turno turno = buscarTurno(salonAsignadoRequestDto.turnoId());

        SalonAsignado salonAsignado = new SalonAsignado();
        salonAsignado.setSalon(salon);
        salonAsignado.setSeccion(seccion);
        salonAsignado.setGrado(grado);
        salonAsignado.setTurno(turno);

        salonAsignadoRepositorio.save(salonAsignado);
        return SalonAsignadoResponseDto.builder()
                .salon(salon.getNombre())
                .seccion(seccion.getNombre())
                .grado(grado.getNombre())
                .turno(turno.getNombre())
                .horario(turno.getHorario())
                .estado("Creado correctamente")
                .mensaje("Guardado correctamente")
                .build();
    }

    @Override
    public void eliminar(Long id) {
        SalonAsignado salonAsignado = buscarId(id);
        salonAsignadoRepositorio.deleteById(salonAsignado.getId());
    }

    @Transactional
    @Override
    public SalonAsignadoResponseDto actualizar(Long id, SalonAsignadoRequestDto salonAsignadoRequestDto) {

        SalonAsignado salonAsignado = buscarId(id);
        Salon salon = buscarSalon(salonAsignadoRequestDto.salonId());
        Seccion seccion = buscarSeccion(salonAsignadoRequestDto.seccionId());
        Grado grado = buscarGrado(salonAsignadoRequestDto.gradoId());
        Turno turno = buscarTurno(salonAsignadoRequestDto.turnoId());

        salonAsignado.setSalon(salon);
        salonAsignado.setSeccion(seccion);
        salonAsignado.setGrado(grado);
        salonAsignado.setTurno(turno);

        salonAsignadoRepositorio.save(salonAsignado);

        return SalonAsignadoResponseDto.builder()
                .salon(salon.getNombre())
                .seccion(seccion.getNombre())
                .grado(grado.getNombre())
                .turno(turno.getNombre())
                .horario(turno.getHorario())
                .estado("Actualizado correctamente")
                .mensaje("Guardado correctamente")
                .build();
    }

    @Override
    public Page<SalonAsignadoResponseDto> listar(int pagina, int tamano) {
        Pageable pageable = PageRequest.of(pagina,tamano);
        Page<SalonAsignado> salonesAsignados = salonAsignadoRepositorio.findAll(pageable);
        return salonesAsignados.map(salonAsignado -> SalonAsignadoResponseDto.builder()
                .salon(salonAsignado.getSalon().getNombre())
                .seccion(salonAsignado.getSeccion().getNombre())
                .grado(salonAsignado.getGrado().getNombre())
                .turno(salonAsignado.getTurno().getNombre())
                .horario(salonAsignado.getTurno().getHorario())
                .build());
    }


    private SalonAsignado buscarId(Long id){
        return  salonAsignadoRepositorio.findById(id).orElseThrow(
                () -> ApiException.userNotFound(MensajeException.VALOR_NO_ENCONTRADO.formatted("salon asignado",id))
        );
    }

    private Salon buscarSalon(Long id){
        return  salonRepositorio.findById(id).orElseThrow(
                () -> ApiException.userNotFound(MensajeException.VALOR_NO_ENCONTRADO.formatted("salon",id))
        );
    }

    private Seccion buscarSeccion(Long id){
        return  seccionRepositorio.findById(id).orElseThrow(
                () -> ApiException.userNotFound(MensajeException.VALOR_NO_ENCONTRADO.formatted("seccion",id))
        );
    }

    private Grado buscarGrado(Long id){
        return  gradoRepositorio.findById(id).orElseThrow(
                () -> ApiException.userNotFound(MensajeException.VALOR_NO_ENCONTRADO.formatted("grado",id))
        );
    }

    private Turno buscarTurno(Long id){
        return  turnoRepositorio.findById(id).orElseThrow(
                () -> ApiException.userNotFound(MensajeException.VALOR_NO_ENCONTRADO.formatted("turno",id))
        );
    }
}