package com.ipn.mx.backalumnos.controllers;

import com.ipn.mx.backalumnos.models.entitites.Archivo;
import com.ipn.mx.backalumnos.services.ArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/archivo")
@CrossOrigin(origins = "*")
public class ArchivoController {

    @Autowired
    private ArchivoService archivoService;
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getArchivo(@PathVariable("id")int id){
        Archivo archivo = archivoService.getArchivoById(id);

        if (archivo != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + archivo.getNombre() + "\"")
                    .contentType(MediaType.parseMediaType(archivo.getTipo()))
                    .body(archivo.getDatos());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> addArchivo(@RequestParam("archivo") MultipartFile archivo) {
        try {
            archivoService.addArchivo(archivo);
            return new ResponseEntity<>("Archivo subido con éxito", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error al subir el archivo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateArchivo(@PathVariable int id, MultipartFile archivo){
        try {
            Archivo archivoActualizado = archivoService.updateArchivo(id, archivo);
            if (archivoActualizado != null) {
                return new ResponseEntity<>("Archivo actualizado con éxito", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Archivo no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            return new ResponseEntity<>("Error al actualizar el archivo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public  ResponseEntity<String> deleteFile(@PathVariable("id") int id){
        if(archivoService.deleteArchivoById(id))
        {
            return  new ResponseEntity<>("Registro Eliminado",HttpStatus.OK);
        }
        return  new ResponseEntity<>("Ha ocurrido un error", HttpStatus.BAD_REQUEST);
    }
}
