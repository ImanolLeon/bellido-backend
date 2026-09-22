package com.example.BelidoMap.services.implementacion;

import com.example.BelidoMap.dto.request.SeccionRequestDto;
import com.example.BelidoMap.dto.response.SeccionResponseDto;
import com.example.BelidoMap.entities.Seccion;
import com.example.BelidoMap.exception.ApiException;
import com.example.BelidoMap.exception.MensajeException;
import com.example.BelidoMap.repository.SeccionRepositorio;
import com.example.BelidoMap.services.reglas.ISeccion;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SeccionService implements ISeccion {

    private SeccionRepositorio seccionRepositorio;


    @Override
    public SeccionResponseDto guardar(SeccionRequestDto seccionRequestDto) {
        Seccion seccion = new Seccion();
        seccion.setNombre(seccionRequestDto.nombre());

        seccionRepositorio.save(seccion);
        return SeccionResponseDto.builder()
                .nombre(seccion.getNombre())
                .estado("Creado correctamente")
                .mensaje("Guardado correctamente")
                .build();
    }

    @Override
    public void eliminar(Long id) {
        Seccion seccion = buscarId(id);
        seccionRepositorio.deleteById(seccion.getId());
    }

    @Transactional
    @Override
    public SeccionResponseDto actualizar(Long id, SeccionRequestDto seccionRequestDto) {

        Seccion seccion = buscarId(id);
        seccion.setNombre(seccionRequestDto.nombre());

        seccionRepositorio.save(seccion);

        return SeccionResponseDto.builder()
                .nombre(seccion.getNombre())
                .estado("Actualizado correctamente")
                .mensaje("Guardado correctamente")
                .build();
    }

    @Override
    public Page<SeccionResponseDto> listar(int pagina, int tamano) {
        Pageable pageable = PageRequest.of(pagina,tamano);
        Page<Seccion> secciones = seccionRepositorio.findAll(pageable);
        return secciones.map(seccion -> SeccionResponseDto.builder()
                .nombre(seccion.getNombre())
                .build());
    }


    private Seccion buscarId(Long id){
        return  seccionRepositorio.findById(id).orElseThrow(
                () -> ApiException.userNotFound(MensajeException.VALOR_NO_ENCONTRADO.formatted("seccion",id))
        );
    }
}