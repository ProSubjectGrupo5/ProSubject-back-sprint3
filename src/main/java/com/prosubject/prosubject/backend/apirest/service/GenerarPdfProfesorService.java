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
import com.prosubject.prosubject.backend.apirest.model.Horario;
import com.prosubject.prosubject.backend.apirest.model.Profesor;
import com.prosubject.prosubject.backend.apirest.service.DBFileStorageService;
import com.prosubject.prosubject.backend.apirest.service.EspacioService;
import com.prosubject.prosubject.backend.apirest.service.HorarioService;
import com.prosubject.prosubject.backend.apirest.service.ProfesorService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;


@Service
public class GenerarPdfProfesorService {

	private static final Logger logger = LoggerFactory.getLogger(GenerarPdfProfesorService.class);

	@Autowired
	private EspacioService espacioService;
	
	@Autowired
	private HorarioService horarioService;

	public ByteArrayInputStream profesorReport(Profesor profesor) {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		List<Espacio> espacios =this.espacioService.espaciosDeUnProfesor(profesor.getId());
		

		try {

			PdfWriter.getInstance(document, out);
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

			
			paragraphHello.add("\nNombre: " + profesor.getNombre().toString());
			paragraphHello.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(paragraphHello);
			paragraphHello.clear();

			paragraphHello
					.add("Apellidos: " + profesor.getApellido1().toString() + " " + profesor.getApellido2().toString());
			paragraphHello.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(paragraphHello);
			paragraphHello.clear();

			paragraphHello.add("DNI: " + profesor.getDni().toString());
			paragraphHello.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(paragraphHello);
			paragraphHello.clear();

			paragraphHello.add("Email: " + profesor.getEmail().toString());
			paragraphHello.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(paragraphHello);
			paragraphHello.clear();

			paragraphHello.add("Telefono: " + profesor.getTelefono().toString());
			paragraphHello.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(paragraphHello);
			paragraphHello.clear();

			paragraphHello.add("Tarifa Premium: " + profesor.getTarifaPremium().toString());
			paragraphHello.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(paragraphHello);
			paragraphHello.clear();

			paragraphHello.add("Expediente validado: " + profesor.getExpedienteValidado().toString());
			paragraphHello.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(paragraphHello);
			paragraphHello.clear();

			paragraphHello.add("Valoración media: " + profesor.getValoracionMedia().toString());
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
			for (int i = 0; i < espacios.size(); i++) {
				Espacio.add("\n- " + espacios.get(i).getAsignatura().getNombre().toString());
				Espacio.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(Espacio);
				Espacio.clear();
			    Espacio e= this.espacioService.findOne(espacios.get(i).getId());
				List<Horario> horarios=this.horarioService.horariosDeUnEspacio(e.getId());
				for (int j = 0; j < horarios.size(); j++) {
					Paragraph Horario = new Paragraph();
					Horario.add("                   * Fecha inicio - fin: " + horarios.get(j).getFechaInicio().toString() +"  -  "+ horarios.get(j).getFechaFin().toString());
					Horario.setAlignment(Element.ALIGN_MIDDLE);
					document.add(Horario);
					Horario.clear();
					
				}
				
			}
			


			document.close();

		} catch (DocumentException ex) {

			logger.error("Error occurred: {0}", ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}
}