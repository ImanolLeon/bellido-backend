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
public class Estudiante {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @OneToOne()
    @JoinColumn(name = "id_usuario", unique = true)
    private Usuario usuario;

    @ManyToOne()
    @JoinColumn(name = "apoderado_id")
    private Apoderado apoderado;

    @ManyToOne()
    @JoinColumn(name = "salon_id")
    private SalonAsignado salon;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "estudiante")
    private List<Matricula> matriculas;



}
