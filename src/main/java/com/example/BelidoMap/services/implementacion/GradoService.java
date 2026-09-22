package com.example.BelidoMap.services.implementacion;

import com.example.BelidoMap.dto.request.GradoRequestDto;
import com.example.BelidoMap.dto.response.GradoResponseDto;
import com.example.BelidoMap.entities.Grado;
import com.example.BelidoMap.exception.ApiException;
import com.example.BelidoMap.exception.MensajeException;
import com.example.BelidoMap.repository.GradoRepositorio;
import com.example.BelidoMap.services.reglas.IGrado;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GradoService implements IGrado {

    private GradoRepositorio gradoRepositorio;


    @Override
    public GradoResponseDto guardar(GradoRequestDto gradoRequestDto) {
        Grado grado = new Grado();
        grado.setNombre(gradoRequestDto.nombre());

        gradoRepositorio.save(grado);
        return GradoResponseDto.builder()
                .nombre(grado.getNombre())
                .estado("Creado correctamente")
                .mensaje("Guardado correctamente")
                .build();
    }

    @Override
    public void eliminar(Long id) {
        Grado grado = buscarId(id);
        gradoRepositorio.deleteById(grado.getId());
    }

    @Transactional
    @Override
    public GradoResponseDto actualizar(Long id, GradoRequestDto gradoRequestDto) {

        Grado grado = buscarId(id);
        grado.setNombre(gradoRequestDto.nombre());

        gradoRepositorio.save(grado);

        return GradoResponseDto.builder()
                .nombre(grado.getNombre())
                .estado("Actualizado correctamente")
                .mensaje("Guardado correctamente")
                .build();
    }

    @Override
    public Page<GradoResponseDto> listar(int pagina, int tamano) {
        Pageable pageable = PageRequest.of(pagina,tamano);
        Page<Grado> grados = gradoRepositorio.findAll(pageable);
        return grados.map(grado -> GradoResponseDto.builder()
                .nombre(grado.getNombre())
                .build());
    }


    private Grado buscarId(Long id){
        return  gradoRepositorio.findById(id).orElseThrow(
                () -> ApiException.userNotFound(MensajeException.VALOR_NO_ENCONTRADO.formatted("grado",id))
        );
    }
}