package com.ipn.mx.backalumnos.models.repository;

import com.ipn.mx.backalumnos.models.entitites.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
}
