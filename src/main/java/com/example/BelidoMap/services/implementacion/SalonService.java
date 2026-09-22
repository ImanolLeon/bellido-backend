package com.example.BelidoMap.services.implementacion;

import com.example.BelidoMap.dto.request.SalonRequestDto;
import com.example.BelidoMap.dto.response.SalonResponseDto;
import com.example.BelidoMap.entities.Salon;
import com.example.BelidoMap.exception.ApiException;
import com.example.BelidoMap.exception.MensajeException;
import com.example.BelidoMap.repository.SalonRepositorio;
import com.example.BelidoMap.services.reglas.ISalon;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SalonService implements ISalon {

    private SalonRepositorio salonRepositorio;


    @Override
    public SalonResponseDto guardar(SalonRequestDto salonRequestDto) {
        Salon salon = new Salon();
        salon.setNombre(salonRequestDto.nombre());
        salon.setCantidadAlumnos(salonRequestDto.cantidadAlumnos());

        salonRepositorio.save(salon);
        return SalonResponseDto.builder()
                .nombre(salon.getNombre())
                .cantidadAlumnos(salon.getCantidadAlumnos())
                .estado("Creado correctamente")
                .mensaje("Guardado correctamente")
                .build();
    }

    @Override
    public void eliminar(Long id) {
        Salon salon = buscarId(id);
        salonRepositorio.deleteById(salon.getId());
    }

    @Transactional
    @Override
    public SalonResponseDto actualizar(Long id, SalonRequestDto salonRequestDto) {

        Salon salon = buscarId(id);
        salon.setNombre(salonRequestDto.nombre());
        salon.setCantidadAlumnos(salonRequestDto.cantidadAlumnos());

        salonRepositorio.save(salon);

        return SalonResponseDto.builder()
                .nombre(salon.getNombre())
                .cantidadAlumnos(salon.getCantidadAlumnos())
                .estado("Actualizado correctamente")
                .mensaje("Guardado correctamente")
                .build();
    }

    @Override
    public Page<SalonResponseDto> listar(int pagina, int tamano) {
        Pageable pageable = PageRequest.of(pagina,tamano);
        Page<Salon> salones = salonRepositorio.findAll(pageable);
        return salones.map(salon -> SalonResponseDto.builder()
                .nombre(salon.getNombre())
                .cantidadAlumnos(salon.getCantidadAlumnos())
                .build());
    }


    private Salon buscarId(Long id){
        return  salonRepositorio.findById(id).orElseThrow(
                () -> ApiException.userNotFound(MensajeException.VALOR_NO_ENCONTRADO.formatted("salon",id))
        );
    }
}