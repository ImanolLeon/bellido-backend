package com.example.BelidoMap.repository;

import com.example.BelidoMap.entities.Apoderado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApoderadoRepositorio extends JpaRepository<Apoderado,Long> {

    void deleteByEstudianteId(Long usuarioId);

}
