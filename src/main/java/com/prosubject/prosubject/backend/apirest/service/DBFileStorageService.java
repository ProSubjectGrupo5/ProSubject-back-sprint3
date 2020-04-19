package com.prosubject.prosubject.backend.apirest.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.prosubject.prosubject.backend.apirest.exception.FileStorageException;
import com.prosubject.prosubject.backend.apirest.exception.MyFileNotFoundException;
import com.prosubject.prosubject.backend.apirest.model.Asignatura;
import com.prosubject.prosubject.backend.apirest.model.DBFile;
import com.prosubject.prosubject.backend.apirest.model.Horario;
import com.prosubject.prosubject.backend.apirest.repository.DBFileRepository;

@Service
public class DBFileStorageService {

    @Autowired
    private DBFileRepository dbFileRepository;

    public DBFile storeFile(MultipartFile file) {
     
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            
            if(fileName.contains("..")) {
                throw new FileStorageException("El archivo contiene una secuencia invalida " + fileName);
            }

            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("No se puede guardar el archivo " + fileName + ". Por favor, inténtelo otra vez.", ex);
        }
    }

    public DBFile getFile(Long fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("No se encontró el archivo con id " + fileId));
    }
    
	public DBFile findOne(final Long fileId) {
		return this.dbFileRepository.findById(fileId).orElse(null);
	}
	
	public void delete(DBFile dBFile) {
		this.dbFileRepository.delete(dBFile);
		
	}
}
