package com.example.BelidoMap.repository;

import com.example.BelidoMap.entities.SalonAsignado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalonAsignadoRepositorio extends JpaRepository<SalonAsignado, Long> {


}