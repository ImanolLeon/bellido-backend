package com.example.BelidoMap.repository;

import com.example.BelidoMap.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    boolean existsByDni(String dni);

    Optional<Usuario> findByDni(String dni);

}
