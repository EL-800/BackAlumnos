package com.ipn.mx.backalumnos.services;

import com.ipn.mx.backalumnos.models.entitites.Alumno;
import com.ipn.mx.backalumnos.models.repository.AlumnoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServiceImp implements AlumnoService{

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public Alumno getAlumnoById(int id) {
        return alumnoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Alumno> getAllAlumnos() {
        return alumnoRepository.findAll();
    }

    @Override
    public Alumno addAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Override
    @Transactional
    public Alumno updateAlumno(int id, Alumno alumno) {
        Alumno currentAlumno = alumnoRepository.findById(id).orElse(null);

        if(currentAlumno != null){
            currentAlumno.setNombre(alumno.getNombre());
            currentAlumno.setPaterno(alumno.getPaterno());
            currentAlumno.setMaterno(alumno.getMaterno());
            currentAlumno.setEmail(alumno.getEmail());
            return alumnoRepository.save(currentAlumno);
        }
        return null;
    }

    @Override
    public boolean deleteAlumnoById(int id) {
        Alumno currentAlumno = alumnoRepository.findById(id).orElse(null);
        if(currentAlumno != null){
            alumnoRepository.delete(currentAlumno);
            return true;
        }
        return  false;
    }


}
