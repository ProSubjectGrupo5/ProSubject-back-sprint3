
package com.prosubject.prosubject.backend.apirest.controller;




import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.annotation.MultipartConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prosubject.prosubject.backend.apirest.model.Alumno;
import com.prosubject.prosubject.backend.apirest.model.DBFile;
import com.prosubject.prosubject.backend.apirest.model.Profesor;
import com.prosubject.prosubject.backend.apirest.payload.UploadFileResponse;
import com.prosubject.prosubject.backend.apirest.service.AlumnoService;
import com.prosubject.prosubject.backend.apirest.service.DBFileStorageService;
import com.prosubject.prosubject.backend.apirest.service.GenerarPdfAlumnoService;
import com.prosubject.prosubject.backend.apirest.service.GenerarPdfProfesorService;
import com.prosubject.prosubject.backend.apirest.service.ProfesorService;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = {"http://localhost:4200", "https://prosubject-v2.herokuapp.com"})
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private DBFileStorageService dbFileStorageService;
    @Autowired
    private AlumnoService aS;
    
    @Autowired
    private ProfesorService profesorService;
    
    @Autowired
    private GenerarPdfAlumnoService gP;
    
    @Autowired
    private GenerarPdfProfesorService gPP;
	


    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
		Map<String, Object> response = new HashMap<String, Object>();
		DBFile dbFile = null;
		
    	if(file.getSize()>1000000) {
    		response.put("mensaje",	 "El archivo que intenta subir es demasiado grande");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
    	}
    	//try {
    	     dbFile = dbFileStorageService.storeFile(file);
    		//}catch(DataAccessException e) {
    		/*	if(file.getSize()>1000000) {
    	    		response.put("mensaje",	 "Es muy grande el archivo");
    				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
    	    	}
    		}*/

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId().toString())
                .toUriString();
        
		return new ResponseEntity<UploadFileResponse>(new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize()), HttpStatus.OK);

       /* return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());*/
    }

   /* @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }
*/
    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
        // Load file from database
        DBFile dbFile = dbFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
        		.contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }
    
    @GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id){
		DBFile file = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			file = this.dbFileStorageService.getFile(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		if(file == null) {
			response.put("mensaje",	 "La file con ID: ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<DBFile>(file, HttpStatus.OK);
	}
    
    @RequestMapping(value = "/pdfAlumno/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> alumnoReport(@PathVariable Long id) {
    	Map<String, Object> response = new HashMap<String, Object>();
    	ByteArrayInputStream bis = null;
    	Alumno a = null;
    	
    	try {
        a = aS.findOne(id);
        
    	bis = this.gP.alumnoReport(a);
    	} catch(DataAccessException e){
    		response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
    		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	if(a == null) {
			response.put("mensaje",	 "El alumno con ID: ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
    
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=ReportAlumno.pdf");
        
        
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
    
    @RequestMapping(value = "/pdfProfesor/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> profesorReport(@PathVariable Long id) {

        Profesor prof = this.profesorService.findOne(id);

        ByteArrayInputStream bis = this.gPP.profesorReport(prof);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=ReportProfesor.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}
