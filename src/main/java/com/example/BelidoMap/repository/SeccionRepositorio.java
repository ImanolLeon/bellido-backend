package com.example.BelidoMap.repository;

import com.example.BelidoMap.entities.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeccionRepositorio extends JpaRepository<Seccion, Long> {


}