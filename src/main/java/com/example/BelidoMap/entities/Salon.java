package com.example.BelidoMap.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Salon {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;


    @Column(name = "cantidad_alumnos")
    private long cantidadAlumnos;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "salon")
    private List<SalonAsignado> salonesAsignados;

}
