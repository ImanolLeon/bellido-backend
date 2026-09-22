package com.example.BelidoMap.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String dni;

    @Column(nullable = false)
    private String password;

    private String nombre;
    private String apellido;
    private String direccion;
    private String correo;
    private String sexo;
    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;

    @Column(nullable = false)
    private boolean admin;
    private long edad;
    private LocalDate fechaRegistro;
    @OneToOne(mappedBy = "usuario")
    private Estudiante estudiante;

    @OneToOne(mappedBy = "usuario")
    private Apoderado apoderado;



}
