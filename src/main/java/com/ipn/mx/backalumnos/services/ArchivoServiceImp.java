package com.ipn.mx.backalumnos.services;

import com.ipn.mx.backalumnos.models.entitites.Archivo;
import com.ipn.mx.backalumnos.models.repository.ArchivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ArchivoServiceImp implements  ArchivoService{

    @Autowired
    private ArchivoRepository archivoRepository;
    @Override
    public Archivo getArchivoById(int id) {
        return archivoRepository.findById(id).orElse(null);
    }

    @Override
    public Archivo addArchivo(MultipartFile file) throws IOException {
        Archivo archivo = new Archivo();
        archivo.setNombre(file.getOriginalFilename());
        archivo.setTipo(file.getContentType());
        archivo.setDatos(file.getBytes());
        return archivoRepository.save(archivo);
    }

    @Override
    public Archivo updateArchivo(int id, MultipartFile file) throws IOException{
        var archivoOptional = archivoRepository.findById(id);
        if (archivoOptional.isPresent()) {
            Archivo archivoExistente = archivoOptional.get();
            archivoExistente.setNombre(file.getOriginalFilename());
            archivoExistente.setTipo(file.getContentType());
            archivoExistente.setDatos(file.getBytes());
            return archivoRepository.save(archivoExistente);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteArchivoById(int id) {
        var archivo = archivoRepository.findById(id);
        if(archivo.isPresent()){
            archivoRepository.delete(archivo.get());
            return true;
        }
        return false;
    }
}
