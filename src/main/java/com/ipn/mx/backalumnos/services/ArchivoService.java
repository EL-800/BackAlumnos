package com.ipn.mx.backalumnos.services;

import com.ipn.mx.backalumnos.models.entitites.Archivo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ArchivoService {
    Archivo getArchivoById(int id);
    Archivo addArchivo(MultipartFile file) throws IOException;
    Archivo updateArchivo(int id, MultipartFile file) throws IOException;
    boolean deleteArchivoById(int id);
}
