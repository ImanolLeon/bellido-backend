package com.example.BelidoMap.services.implementacion;

import com.example.BelidoMap.dto.request.TurnoRequestDto;
import com.example.BelidoMap.dto.response.TurnoResponseDto;
import com.example.BelidoMap.entities.Turno;
import com.example.BelidoMap.exception.ApiException;
import com.example.BelidoMap.exception.MensajeException;
import com.example.BelidoMap.repository.TurnoRepositorio;
import com.example.BelidoMap.services.reglas.ITurno;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TurnoService implements ITurno {

    private TurnoRepositorio turnoRepositorio;


    @Override
    public TurnoResponseDto guardar(TurnoRequestDto turnoRequestDto) {
        Turno turno = new Turno();
        turno.setNombre(turnoRequestDto.nombre());
        turno.setHorario(turnoRequestDto.horario());

        turnoRepositorio.save(turno);
        return TurnoResponseDto.builder()
                .nombre(turno.getNombre())
                .horario(turno.getHorario())
                .estado("Creado correctamente")
                .mensaje("Guardado correctamente")
                .build();
    }

    @Override
    public void eliminar(Long id) {
        Turno turno = buscarId(id);
        turnoRepositorio.deleteById(turno.getId());
    }

    @Transactional
    @Override
    public TurnoResponseDto actualizar(Long id, TurnoRequestDto turnoRequestDto) {

        Turno turno = buscarId(id);
        turno.setNombre(turnoRequestDto.nombre());
        turno.setHorario(turnoRequestDto.horario());

        turnoRepositorio.save(turno);

        return TurnoResponseDto.builder()
                .nombre(turno.getNombre())
                .horario(turno.getHorario())
                .estado("Actualizado correctamente")
                .mensaje("Guardado correctamente")
                .build();
    }

    @Override
    public Page<TurnoResponseDto> listar(int pagina, int tamano) {
        Pageable pageable = PageRequest.of(pagina,tamano);
        Page<Turno> turnos = turnoRepositorio.findAll(pageable);
        return turnos.map(turno -> TurnoResponseDto.builder()
                .nombre(turno.getNombre())
                .horario(turno.getHorario())
                .build());
    }


    private Turno buscarId(Long id){
        return  turnoRepositorio.findById(id).orElseThrow(
                () -> ApiException.userNotFound(MensajeException.VALOR_NO_ENCONTRADO.formatted("turno",id))
        );
    }
}