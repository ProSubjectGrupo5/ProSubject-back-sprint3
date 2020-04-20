package com.prosubject.prosubject.backend.apirest.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
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
        List<Espacio> es = new ArrayList<Espacio>();
     //  List<String> espacios = new ArrayList<String>();
        List<Valoracion> val = new ArrayList<Valoracion>(); 
      //  List<String> valoraciones = new ArrayList<String>();
       /* if(eS.espaciosDeUnAlumno(a.getId()).isEmpty()) {
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
        
        	*/
        	
        try {
        	val = this.vS.valoracionesPorAlumnoId(a.getId());
        	es = this.eS.espaciosDeUnAlumno(a.getId());
        	PdfWriter.getInstance(document, out);
            // document.open();
             //document.add(table);
         	document.open();
         	
         	Paragraph paragraphHello = new Paragraph();
         	
         	
         	document.add(new Paragraph("ProSubject",
					FontFactory.getFont("arial",   // fuente
							35,                            // tamaño
							Font.ITALIC,                   // estilo
							BaseColor.ORANGE)));             // color
			
			document.add(new Paragraph("\nDATOS PERSONALES\n",
					FontFactory.getFont("arial",   // fuente
							16,                            // tamaño
							Font.ITALIC,                   // estilo
							BaseColor.RED)));             // color
         	
			paragraphHello.add("\nNombre: " + a.getNombre().toString());
			paragraphHello.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(paragraphHello);
			paragraphHello.clear();

			paragraphHello
					.add("Apellidos: " + a.getApellido1().toString() + " " + a.getApellido2().toString());
			paragraphHello.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(paragraphHello);
			paragraphHello.clear();

			paragraphHello.add("DNI: " + a.getDni().toString());
			paragraphHello.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(paragraphHello);
			paragraphHello.clear();

			paragraphHello.add("Email: " + a.getEmail().toString());
			paragraphHello.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(paragraphHello);
			paragraphHello.clear();

			paragraphHello.add("Telefono: " + a.getTelefono().toString());
			paragraphHello.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(paragraphHello);
			paragraphHello.clear();
			
			paragraphHello.add("Universidad: " + a.getUniversidad().getNombre());
			paragraphHello.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(paragraphHello);
			paragraphHello.clear();

			paragraphHello.add("Facultad: " +a.getFacultad().getNombre());
			paragraphHello.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(paragraphHello);
			paragraphHello.clear();

			paragraphHello.add("Grado: " + a.getGrado().getNombre());
			paragraphHello.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(paragraphHello);
			paragraphHello.clear();
			
			Paragraph Espacio = new Paragraph();
			document.add(new Paragraph("\nESPACIOS\n",
					FontFactory.getFont("arial",   // fuente
							16,                            // tamaño
							Font.ITALIC,                   // estilo
							BaseColor.RED)));             // color
			Espacio.clear();
			
			
			for(Espacio e :  es) {
	        	Espacio.add(e.getAsignatura().getNombre());
	        	Espacio.setAlignment(Element.ALIGN_JUSTIFIED);
	        	document.add(Espacio);
	        	Espacio.clear();
	        }
        
			Paragraph Valoracion = new Paragraph();
			document.add(new Paragraph("\nValoraciones\n",
					FontFactory.getFont("arial",   // fuente
							16,                            // tamaño
							Font.ITALIC,                   // estilo
							BaseColor.RED)));             // color
			Valoracion.clear();
			
			
			 for(Valoracion v :  val) {
		        	Valoracion.add("Puntuación: "+v.getPuntuacion()+" / Comentario: "+v.getComentario());
		        	Valoracion.setAlignment(Element.ALIGN_JUSTIFIED);
		        	document.add(Valoracion);
		        	Valoracion.clear();
		        }
         

            document.close();

        } catch (DocumentException ex) {

            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}