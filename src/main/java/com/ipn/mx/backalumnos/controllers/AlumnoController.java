package com.ipn.mx.backalumnos.controllers;

import com.ipn.mx.backalumnos.models.entitites.Alumno;
import com.ipn.mx.backalumnos.services.AlumnoService;
import com.ipn.mx.backalumnos.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/alumno")
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;
    @Autowired
    private EmailService emailService;
    @GetMapping
    public ResponseEntity<List<Alumno>> getAllAlumnos(){
        return new ResponseEntity<>(alumnoService.getAllAlumnos(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Alumno> getAlumnoById(@PathVariable("id") int id){
        var alumno = alumnoService.getAlumnoById(id);
        if(alumno != null)
            return new ResponseEntity<>(alumno, HttpStatus.OK);
        else
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<Alumno> addAlumno(@RequestBody Alumno alumno){
        var newAlumno = alumnoService.addAlumno(alumno);
        emailService.sendEmail(newAlumno.getEmail(),"Practica Email","Usuario Registrado");
        return new ResponseEntity<>(newAlumno, HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<Alumno> updateAlumno(@PathVariable("id") int id, @RequestBody Alumno alumno){
        var editedAlumno = alumnoService.updateAlumno(id,alumno);
        emailService.sendEmail(editedAlumno.getEmail(),"Practica Email","Usuario Editado " + editedAlumno.getEmail());
        return new ResponseEntity<>(alumnoService.updateAlumno(id, alumno), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public  ResponseEntity<String> deleteAlumno(@PathVariable("id") int id){
        String email = alumnoService.getAlumnoById(id).getEmail();
        if(alumnoService.deleteAlumnoById(id))
        {
            if(email != null)
            {
                emailService.sendEmail(email,"Cuenta Eliminada", "Has borrado tu cuenta");
                return  new ResponseEntity<>("Registro Eliminado",HttpStatus.OK);
            }
        }
        return  new ResponseEntity<>("Ha ocurrido un error", HttpStatus.BAD_REQUEST);
    }
}
