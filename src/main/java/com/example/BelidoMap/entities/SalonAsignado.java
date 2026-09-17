package com.example.BelidoMap.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "salon_asginado")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalonAsignado {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "salon_id")
    private Salon salon;

    @ManyToOne()
    @JoinColumn(name = "seccion_id")
    private Seccion seccion;

    @ManyToOne()
    @JoinColumn(name = "grado_id")
    private Grado grado;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "salon")
    private List<Estudiante> estudiantes;

    @ManyToOne
    @JoinColumn(name = "turno_id")
    private Turno turno;

}
