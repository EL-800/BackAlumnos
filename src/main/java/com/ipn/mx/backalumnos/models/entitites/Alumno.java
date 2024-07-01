package com.ipn.mx.backalumnos.models.entitites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="Alumno")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name="paterno", length = 50, nullable = false)
    private String paterno;

    @Column(name="materno", length = 50, nullable = false)
    private String materno;

    @Column(name="email", length = 50, nullable = false)
    private String email;
}
