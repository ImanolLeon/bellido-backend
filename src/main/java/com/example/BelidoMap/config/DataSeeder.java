package com.example.BelidoMap.config;

import com.example.BelidoMap.entities.Usuario;
import com.example.BelidoMap.repository.UsuarioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (!usuarioRepositorio.existsByDni("61230119")) {
            Usuario usuario = Usuario.builder()
                    .dni("61230119")
                    .password(passwordEncoder.encode("123456"))
                    .nombre("manolito Rojas")
                    .apellido("Leon Cordova")
                    .direccion("santa isabel")
                    .correo("imanolleon@gmail.com")
                    .sexo("masculino")
                    .fechaNacimiento("22/06/2005")
                    .admin(true)
                    .edad(20)
                    .fechaRegistro(LocalDate.now())
                    .build();
            usuarioRepositorio.save(usuario);
        }
    }
}