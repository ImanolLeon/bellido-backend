package com.example.BelidoMap.repository;

import com.example.BelidoMap.entities.Grado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradoRepositorio extends JpaRepository<Grado, Long> {


}