package com.ipn.mx.backalumnos.services;


import com.ipn.mx.backalumnos.models.entitites.Alumno;

import java.util.List;

public interface AlumnoService {
    Alumno getAlumnoById(int id);
    List<Alumno> getAllAlumnos();
    Alumno addAlumno(Alumno alumno);
    Alumno updateAlumno(int id, Alumno alumno);
    boolean deleteAlumnoById(int id);
}
