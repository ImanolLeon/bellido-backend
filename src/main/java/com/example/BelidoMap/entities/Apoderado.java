package com.example.BelidoMap.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Apoderado {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String parentesco;

    @OneToOne()
    @JoinColumn(name = "usuario_id", unique = true)
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "apoderado")
    private List<Estudiante> estudiante;

}
