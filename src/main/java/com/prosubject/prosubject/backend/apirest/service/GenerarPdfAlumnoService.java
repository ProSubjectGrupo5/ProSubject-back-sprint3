package com.prosubject.prosubject.backend.apirest.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.prosubject.prosubject.backend.apirest.model.Alumno;
import com.prosubject.prosubject.backend.apirest.model.Espacio;
import com.prosubject.prosubject.backend.apirest.model.Valoracion;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


@Service
public class GenerarPdfAlumnoService {
	
	@Autowired
	private EspacioService eS;
	
	@Autowired
	private ValoracionService vS;
	
    private static final Logger logger = LoggerFactory.getLogger(GenerarPdfAlumnoService.class);

    public ByteArrayInputStream alumnoReport(Alumno a) {
    	
    
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        List<Espacio> es = this.eS.espaciosDeUnAlumno(a.getId());
        List<String> espacios = new ArrayList<String>();
        List<Valoracion> val = this.vS.valoracionesPorAlumnoId(a.getId());
        List<String> valoraciones = new ArrayList<String>();
        if(eS.espaciosDeUnAlumno(a.getId()).isEmpty()) {
        	espacios.add("El alumno no esta inscrito en ningún espacio");
        }else {
        for(Espacio e :  es) {
        	espacios.add(e.getAsignatura().getNombre());
        }
        }
        
        if(vS.valoracionesPorAlumnoId(a.getId())==null) {
        	espacios.add("El alumno no ha realizado ninguna valoración");
        }else {
        for(Valoracion v :  val) {
        	valoraciones.add("Puntuación: "+v.getPuntuacion()+" / Comentario: "+v.getComentario());
        }
        }
        
        	
        	
        try {
        	PdfWriter.getInstance(document, out);
            // document.open();
             //document.add(table);
         	document.open();
         	document.add(new Paragraph("Nombre: "+a.getNombre()));
         	document.add(new Paragraph("Apellidos: "+a.getApellido1()+" "+a.getApellido2()));
         	document.add(new Paragraph("DNI: "+a.getDni()));
         	document.add(new Paragraph("Email: "+a.getEmail()));
         	document.add(new Paragraph("Teléfono: "+a.getTelefono()));
         	document.add(new Paragraph("Universidad: "+a.getUniversidad().getNombre()));
         	document.add(new Paragraph("Facultad: "+a.getFacultad().getNombre()));
         	document.add(new Paragraph("Grado: "+a.getGrado().getNombre()));
         	document.add(new Paragraph("Espacios donde está inscrito: "+espacios));
         	document.add(new Paragraph("Valoraciones dadas: "+valoraciones));
         	

            document.close();

        } catch (DocumentException ex) {

            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}