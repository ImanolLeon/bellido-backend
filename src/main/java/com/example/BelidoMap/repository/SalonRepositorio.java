package com.example.BelidoMap.repository;

import com.example.BelidoMap.entities.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalonRepositorio extends JpaRepository<Salon, Long> {


}