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
public class Turno {
    @Id @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "turno")
    private List<SalonAsignado> salonesAsignados;

    private String nombre;

    private String horario;

}
